# Production deployment – branch `kec_claude` (Spring Boot 4.1.1)

Target: Ubuntu VPS (22.04 or 24.04), external Apache Tomcat 11, MySQL 8, optional nginx in front for HTTPS.
Nothing in the database schema changes (`ddl-auto=none`), so the old version can be put back at any time (§9).

## 1. Software requirements

| Software | Version | Notes |
|---|---|---|
| Java (runtime) | **OpenJDK 25** (LTS) – tested with 25.0.4 | `openjdk-25-jre-headless` from the Ubuntu archive (22.04 and 24.04). Java 8/11 will **not** run this build. |
| Apache Tomcat | **11.0.x, at least 11.0.24** (the version the build and tests use) | Tomcat 11 = Jakarta Servlet 6.1. **Tomcat 8/9/10 will not work.** Not in apt – download from Apache. |
| MySQL server | **8.0** – tested with 8.0.46 | Use the existing production DB as it is. Keep its current `lower_case_table_names` setting. MySQL 8.4 LTS should work (driver mysql-connector-j 9.7) but was not tested – don't upgrade the DB in the same step. |
| nginx (optional) | Ubuntu package | Reverse proxy + HTTPS (Let's Encrypt via certbot). |
| Outbound network | – | `smtp.gmail.com:587` (mail), Google/Firebase APIs over 443 (`/rn`, `/rs`, attendance/leave), Textlocal SMS API. |
| Build machine | JDK 25 + Maven 3.6.3+ | Build on your PC (the tests need the local MySQL copy), copy only the WAR to the VPS. |

The WAR already contains: all libraries, JSPs, static files, the Firebase/Google key files and `SMS.properties`
(same as before). It does **not** contain DB or SMTP credentials – those come from environment variables (§4).

## 2. Build the WAR (on your PC / WSL)

```bash
cd ~/college && git checkout kec_claude && git pull
export JAVA_HOME=/usr/lib/jvm/java-25-openjdk-amd64
mvn clean package                    # build + tests (local MySQL + application-local.properties)
# result: target/spring-boot-example-1.0-SNAPSHOT.war
scp target/spring-boot-example-1.0-SNAPSHOT.war user@your-vps:/tmp/ROOT.war
```
(`mvn clean package -DskipTests` builds without tests, e.g. on a machine without the local DB.)

## 3. Install Java and Tomcat on the VPS (once)

```bash
sudo apt update
sudo apt install -y openjdk-25-jre-headless
java -version                         # must say 25

# Tomcat 11 (pick the newest 11.0.x from https://tomcat.apache.org/download-11.cgi, >= 11.0.24)
TV=11.0.24
cd /tmp && wget https://archive.apache.org/dist/tomcat/tomcat-11/v$TV/bin/apache-tomcat-$TV.tar.gz
sudo useradd -r -m -U -d /opt/tomcat -s /bin/false tomcat 2>/dev/null || true
sudo tar xzf apache-tomcat-$TV.tar.gz -C /opt/tomcat
sudo ln -sfn /opt/tomcat/apache-tomcat-$TV /opt/tomcat/latest
sudo rm -rf /opt/tomcat/latest/webapps/*          # remove the demo apps (ROOT, manager, examples, docs)
sudo chown -R tomcat:tomcat /opt/tomcat
```

Logo used in the attendance-report and leave PDFs (absolute path in `DocUtils`; skip if it already exists on the server):
```bash
sudo mkdir -p /sites/default/files/compressed
sudo cp <logo.png from src/main/webapp/sites/default/files/compressed/> /sites/default/files/compressed/logo.png
sudo chmod -R a+r /sites
```

## 4. Configuration

### 4.1 Credentials – `/opt/tomcat/latest/bin/setenv.sh`
```bash
#!/bin/sh
export DB_URL='jdbc:mysql://localhost:3306/keckacin_college2'
export DB_USERNAME='****'
export DB_PASSWORD='****'
export MAIL_USERNAME='****@keck.ac.in'
export MAIL_PASSWORD='****'          # Gmail app password
export JAVA_OPTS="-Xms512m -Xmx1536m -Djava.awt.headless=true -Dfile.encoding=UTF-8"
# keep the server's current time zone; to force IST instead add: -Duser.timezone=Asia/Kolkata
```
```bash
sudo chown tomcat:tomcat /opt/tomcat/latest/bin/setenv.sh
sudo chmod 750 /opt/tomcat/latest/bin/setenv.sh      # contains passwords – not world-readable
```
Use the **rotated** credentials here (the old ones are in git history). Adjust `-Xmx` to the VPS RAM
(about half of it). Alternative to env vars: a `application-local.properties` file plus
`-Dspring.profiles.active=local -Dspring.config.additional-location=file:/opt/tomcat/conf/` – env vars are simpler.

### 4.2 Tomcat `conf/server.xml`
Change only the HTTP connector (and add the valve if nginx is in front):
```xml
<!-- behind nginx: listen on localhost only -->
<Connector port="8080" address="127.0.0.1" protocol="HTTP/1.1"
           connectionTimeout="20000" URIEncoding="UTF-8" />
...
<Host name="localhost" appBase="webapps" unpackWARs="true" autoDeploy="false">
  <!-- behind nginx: use the client's IP and https from X-Forwarded-* headers (correct redirects after login) -->
  <Valve className="org.apache.catalina.valves.RemoteIpValve"
         remoteIpHeader="X-Forwarded-For" protocolHeader="X-Forwarded-Proto" />
```
`unpackWARs="true"` (the default) is **required**: `/rs` (Google Calendar) reads its `.p12` key as a plain file.
Without nginx: use `port="80"` (needs `AmbientCapabilities=CAP_NET_BIND_SERVICE` in the unit below) and no valve.

### 4.3 systemd – `/etc/systemd/system/tomcat.service`
```ini
[Unit]
Description=Apache Tomcat 11 (KEC college)
After=network.target mysql.service

[Service]
Type=forking
User=tomcat
Group=tomcat
Environment=JAVA_HOME=/usr/lib/jvm/java-25-openjdk-amd64
Environment=CATALINA_HOME=/opt/tomcat/latest
Environment=CATALINA_BASE=/opt/tomcat/latest
Environment=CATALINA_PID=/opt/tomcat/latest/temp/tomcat.pid
ExecStart=/opt/tomcat/latest/bin/startup.sh
ExecStop=/opt/tomcat/latest/bin/shutdown.sh
SuccessExitStatus=143
Restart=on-failure
# AmbientCapabilities=CAP_NET_BIND_SERVICE   # only if Tomcat listens on port 80 itself

[Install]
WantedBy=multi-user.target
```
```bash
sudo systemctl daemon-reload && sudo systemctl enable tomcat
```

### 4.4 nginx (optional, recommended) – `/etc/nginx/sites-available/keck`
```nginx
server {
    server_name keck.ac.in www.keck.ac.in;
    client_max_body_size 25m;                 # = spring.servlet.multipart.max-request-size
    location / {
        proxy_pass http://127.0.0.1:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_read_timeout 300s;
    }
    listen 80;
}
```
```bash
sudo apt install -y nginx certbot python3-certbot-nginx
sudo ln -s /etc/nginx/sites-available/keck /etc/nginx/sites-enabled/
sudo nginx -t && sudo systemctl reload nginx
sudo certbot --nginx -d keck.ac.in -d www.keck.ac.in        # HTTPS
sudo ufw allow 'Nginx Full'; sudo ufw allow OpenSSH          # keep 8080 and 3306 closed to the internet
```

### 4.5 MySQL
No schema changes. The app user needs `SELECT, INSERT, UPDATE, DELETE` on `keckacin_college2`
(the old `ddl-auto=update` needed `ALTER`/`CREATE`; `none` does not). Do not expose 3306 publicly.

## 5. Deploy (first time and every update)

```bash
# 1. backup the DB
mysqldump -u <user> -p --single-transaction --routines keckacin_college2 | gzip > ~/backup-$(date +%F-%H%M).sql.gz
# 2. stop, replace the app, start
sudo systemctl stop tomcat
sudo cp /opt/tomcat/latest/webapps/ROOT.war ~/ROOT.war.previous 2>/dev/null || true
sudo rm -rf /opt/tomcat/latest/webapps/ROOT /opt/tomcat/latest/webapps/ROOT.war /opt/tomcat/latest/work/Catalina
sudo cp /tmp/ROOT.war /opt/tomcat/latest/webapps/ROOT.war
sudo chown tomcat:tomcat /opt/tomcat/latest/webapps/ROOT.war
sudo systemctl start tomcat
# 3. watch the start (about 20-40 s; look for "Started Application")
sudo tail -f /opt/tomcat/latest/logs/catalina.out
```
The WAR **must** be named `ROOT.war` – the site uses absolute links (`/jsp/...`, `/approval/...`), so it has to run at `/`.

## 6. Check after deploy

* `curl -I http://127.0.0.1:8080/` → 200; home page, menu pages, a PDF page (e.g. `/approval/akuapproval`).
* Log in as Admin, Faculty, HOD, SM/SK; upload a document; leave request (mail with PDF); password reset mail.
* `/rn`, `/rs` and store workflows by hand (checklist: `docs/PROJECT_ANALYSIS.md` §9).
* Expected harmless log lines: modelmapper `sun.misc.Unsafe` warning, Hibernate `@Temporal` deprecation.

## 7. Logs and operations

| What | Where / command |
|---|---|
| App + Tomcat log | `/opt/tomcat/latest/logs/catalina.out` (rotate with logrotate, `copytruncate`) |
| Access log | `/opt/tomcat/latest/logs/localhost_access_log.*.txt` |
| Start / stop / status | `sudo systemctl start|stop|restart|status tomcat` |
| Temp files (generated PDFs) | `/opt/tomcat/latest/temp` (Tomcat's `java.io.tmpdir`, must be writable by `tomcat`) |

## 8. Differences from the old production setup

* Java 8 + Tomcat 8 → **Java 25 + Tomcat 11** (the old Tomcat cannot run this build – install side by side, §3).
* DB/mail credentials from **env vars** (`setenv.sh`), no longer inside the WAR.
* `ddl-auto=update` → `none`: Hibernate no longer alters the production schema at startup.
* Upload limit: 20 MB per file, 25 MB per request (before: unlimited).
* Embedded PDFs come from the site itself (no Google viewer); `X-Frame-Options: SAMEORIGIN`.
* Everything else (URLs, parameters, sessions, logins) is unchanged – see `docs/UPGRADE_NOTES.md`.

## 9. Rollback

The schema is untouched, so rollback = start the previous setup again:
`sudo systemctl stop tomcat`, then start the old Tomcat 8 / Java 8 with the old WAR (or copy `~/ROOT.war.previous`
back if it was already on Tomcat 11). Restore the DB backup from §5 only if data must be reset.
