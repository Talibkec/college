/*
 * Faculty profile uploads (/uploadfile/facultyFileUpload?isProfilePic=No|Yes): checks the form, posts it with fetch
 * and goes back to the own profile when saved; errors are shown above the form.
 */
(function () {
    var MAX_KB = 5 * 1024; // 5 MB (decision 2026-10-28; before: 10 KB), checked by the server as well
    var form = document.getElementById('fileUploadForm');
    var file = document.getElementById('facultydocumentsFile');
    var isLink = document.getElementById('isLink');
    var msg = document.getElementById('msg');
    var button = document.getElementById('btnSubmit');
    var photo = form.elements.isProfilePic.value === 'Yes';

    document.getElementById('maxSize').textContent = '5 MB';

    function show(text, ok) {
        msg.textContent = text;
        msg.className = 'mb-4 p-3 rounded border ' + (ok ? 'bg-green-50 border-green-400 text-green-800' : 'bg-red-50 border-red-400 text-red-800');
    }

    function chosen() {
        return file.files && file.files.length ? file.files[0] : null;
    }

    file.addEventListener('change', function () {
        var f = chosen();
        document.getElementById('size').textContent = f ? 'Selected: ' + Math.ceil(f.size / 1024) + ' KB' : '';
        if (f && f.size > MAX_KB * 1024) show('The file is ' + Math.ceil(f.size / 1024) + ' KB; at most 5 MB are allowed.', false);
        else msg.className = 'hidden';
    });

    if (isLink) {
        isLink.addEventListener('change', function () {
            document.getElementById('linkBox').classList.toggle('hidden', !isLink.checked);
            document.getElementById('chooseFileBox').classList.toggle('hidden', isLink.checked);
        });
    }

    form.addEventListener('submit', function (e) {
        e.preventDefault();
        var link = isLink && isLink.checked;
        var f = chosen();
        if (!photo && !form.elements.facultydocumentsHeader.value.trim()) {
            show('Please enter a heading.', false);
            form.elements.facultydocumentsHeader.focus();
            return;
        }
        if (link && !/^https?:\/\/\S+$/i.test(document.getElementById('linkAddress').value.trim())) {
            show('Please enter the link address, starting with https:// or http://.', false);
            document.getElementById('linkAddress').focus();
            return;
        }
        if (!link && !f) {
            show(photo ? 'Please choose a photo.' : 'Please choose a file.', false);
            return;
        }
        if (!link && f.size > MAX_KB * 1024) {
            show('The file is ' + Math.ceil(f.size / 1024) + ' KB; at most 5 MB are allowed.', false);
            return;
        }
        if (photo && !/\.(jpe?g|png)$/i.test(f.name)) {
            show('Please choose a JPG or PNG photo.', false);
            return;
        }
        var data = new FormData(form);
        if (link) {
            data.set('isLink', 'true');
            data.set('facultydocumentsFile', new Blob([]), '');
        }
        button.disabled = true;
        show('Uploading...', true);
        fetch('/uploadfile/facultyFileUpload', {method: 'POST', body: data, credentials: 'same-origin'})
            .then(function (r) { return r.text().then(function (t) { return {ok: r.ok, status: r.status, text: t}; }); })
            .then(function (r) {
                button.disabled = false;
                if (r.ok) {
                    show('Saved. Opening your profile...', true);
                    window.location.href = '/mydashboard';
                } else if (r.status === 413) {
                    show('The file is too big.', false);
                } else {
                    show(r.text && r.text.length < 300 && r.text.indexOf('<') < 0 ? r.text : 'Upload failed (' + r.status + ').', false);
                }
            })
            .catch(function () {
                button.disabled = false;
                show('Upload failed: no connection to the server.', false);
            });
    });
})();
