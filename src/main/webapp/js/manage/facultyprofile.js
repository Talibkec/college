/*
 * Profile editor of a faculty member (/uploadfile/editfacultydetails). Keeps the headings and their entries in a
 * list, redraws them after every change and posts them with the page's original parameter names; the server checks
 * who may save (the faculty member and Admin) and validates everything again.
 */
(function () {
    var QUICK = ['Qualifications', 'Area of Research', 'Professional Experience', 'Administrative Responsibilities',
        'Subjects Taught', 'Publications', 'Membership'];
    var headings = [];
    var dirty = false;
    var list = document.getElementById('headings');
    var form = document.getElementById('editDetailsForm');
    var message = document.getElementById('profile-message');

    try {
        headings = JSON.parse(document.getElementById('headings-data').textContent) || [];
    } catch (e) {
        headings = [];
    }

    function el(tag, attrs, children) {
        var node = document.createElement(tag);
        Object.keys(attrs || {}).forEach(function (k) {
            if (k === 'text') node.textContent = attrs[k];
            else if (k === 'onclick') node.addEventListener('click', attrs[k]);
            else if (k === 'oninput') node.addEventListener('input', attrs[k]);
            else node.setAttribute(k, attrs[k]);
        });
        (children || []).forEach(function (c) { if (c) node.appendChild(c); });
        return node;
    }

    function icon(name, title, onclick, disabled, extra) {
        var b = el('button', {type: 'button', 'class': 'pe-icon' + (extra ? ' ' + extra : ''), title: title, 'aria-label': title, onclick: onclick},
            [el('i', {'class': 'fa ' + name})]);
        b.disabled = !!disabled;
        return b;
    }

    function changed() {
        dirty = true;
        document.getElementById('dirty-note').classList.remove('hidden');
    }

    function move(arr, i, d) {
        var j = i + d;
        if (j < 0 || j >= arr.length) return;
        var t = arr[i]; arr[i] = arr[j]; arr[j] = t;
        changed();
        render();
    }

    function autosize(t) {
        t.style.height = 'auto';
        t.style.height = Math.max(t.scrollHeight, 38) + 'px';
    }

    function render(focus) {
        list.innerHTML = '';
        document.getElementById('no-headings').classList.toggle('hidden', headings.length > 0);
        headings.forEach(function (h, i) {
            var title = el('input', {type: 'text', maxlength: '255', 'class': 'pe-input font-semibold', placeholder: 'Heading, e.g. Qualifications',
                'aria-label': 'Heading ' + (i + 1), oninput: function () { h.title = this.value; this.classList.remove('pe-invalid'); changed(); }});
            title.value = h.title || '';
            title.dataset.heading = i;

            var rows = el('div', {'class': 'space-y-2 mt-3 pl-2 border-l-4 border-blue-200'});
            if (!h.values.length) rows.appendChild(el('p', {'class': 'text-sm text-gray-500 italic', text: 'No entries yet.'}));
            h.values.forEach(function (v, j) {
                var text = el('textarea', {rows: '1', maxlength: '500', 'class': 'pe-input', placeholder: 'Entry, e.g. Ph.D. (IIT Patna)',
                    'aria-label': 'Entry ' + (j + 1) + ' of heading ' + (i + 1), oninput: function () { v.text = this.value; autosize(this); changed(); }});
                text.value = v.text || '';
                text.dataset.value = i + '.' + j;
                rows.appendChild(el('div', {'class': 'flex items-start gap-2'}, [
                    el('span', {'class': 'mt-2 text-gray-400', text: '•'}), text,
                    icon('fa-arrow-up', 'Move entry up', function () { move(h.values, j, -1); }, j === 0),
                    icon('fa-arrow-down', 'Move entry down', function () { move(h.values, j, 1); }, j === h.values.length - 1),
                    icon('fa-times', 'Delete entry', function () { h.values.splice(j, 1); changed(); render(); }, false, 'pe-danger')
                ]));
            });
            var addValue = el('button', {type: 'button', 'class': 'mt-2 text-blue-700 hover:underline text-sm', onclick: function () {
                h.values.push({id: null, text: ''}); changed(); render('v' + i + '.' + (h.values.length - 1));
            }}, [el('i', {'class': 'fa fa-plus'}), document.createTextNode(' Add entry')]);

            list.appendChild(el('div', {'class': 'border border-gray-300 rounded-lg p-4 bg-gray-50'}, [
                el('div', {'class': 'flex items-center gap-2'}, [
                    el('span', {'class': 'shrink-0 w-7 h-7 rounded-full bg-blue-700 text-white text-sm flex items-center justify-center', text: String(i + 1)}),
                    title,
                    icon('fa-arrow-up', 'Move heading up', function () { move(headings, i, -1); }, i === 0),
                    icon('fa-arrow-down', 'Move heading down', function () { move(headings, i, 1); }, i === headings.length - 1),
                    icon('fa-trash', 'Delete heading', function () {
                        var filled = h.values.filter(function (v) { return (v.text || '').trim(); }).length;
                        if (filled && !window.confirm('Delete the heading "' + (h.title || '') + '" and its ' + filled + ' entries?')) return;
                        headings.splice(i, 1); changed(); render();
                    }, false, 'pe-danger')
                ]),
                rows, addValue
            ]));
        });
        list.querySelectorAll('textarea').forEach(autosize);
        renderQuick();
        if (focus) {
            var target = focus.charAt(0) === 'h' ? list.querySelector('[data-heading="' + focus.substring(1) + '"]')
                : list.querySelector('[data-value="' + focus.substring(1) + '"]');
            if (target) target.focus();
        }
    }

    function renderQuick() {
        var box = document.getElementById('quick-headings');
        box.innerHTML = '';
        var present = headings.map(function (h) { return (h.title || '').trim().toLowerCase(); });
        QUICK.filter(function (q) { return present.indexOf(q.toLowerCase()) < 0; }).forEach(function (q) {
            box.appendChild(el('button', {type: 'button', 'class': 'pe-chip', text: '+ ' + q, onclick: function () { addHeading(q); }}));
        });
    }

    function addHeading(title) {
        headings.push({id: null, title: title || '', values: [{id: null, text: ''}]});
        changed();
        render(title ? 'v' + (headings.length - 1) + '.0' : 'h' + (headings.length - 1));
    }

    function show(text, ok) {
        message.textContent = text;
        message.className = 'mb-4 p-3 rounded border ' + (ok ? 'bg-green-50 border-green-400 text-green-800' : 'bg-red-50 border-red-400 text-red-800');
        message.scrollIntoView({behavior: 'smooth', block: 'center'});
    }

    function value(id) {
        return document.getElementById(id).value.trim();
    }

    form.addEventListener('input', function (e) { if (e.target.closest('section')) changed(); });
    document.getElementById('add-heading').addEventListener('click', function () { addHeading(''); });

    form.addEventListener('submit', function (e) {
        e.preventDefault();
        var firstEmpty = -1;
        headings.forEach(function (h, i) { if (!(h.title || '').trim() && firstEmpty < 0) firstEmpty = i; });
        if (firstEmpty >= 0) {
            var t = list.querySelector('[data-heading="' + firstEmpty + '"]');
            t.classList.add('pe-invalid');
            t.focus();
            show('Heading ' + (firstEmpty + 1) + ' has no title. Type one or delete the heading.', false);
            return;
        }
        if (value('facultyMobNo') && !/^[0-9]{6,15}$/.test(value('facultyMobNo'))) {
            document.getElementById('facultyMobNo').focus();
            show('The mobile number may only contain 6 to 15 digits.', false);
            return;
        }
        var details = {facultyKeyProps: headings.map(function (h, i) {
            return {keyPropertyId: h.id, keyPropertyName: h.title.trim(), keyPropsOrder: i + 1,
                keyPropVals: h.values.filter(function (v) { return (v.text || '').trim(); }).map(function (v, j) {
                    return {keyPropValuesId: v.id, keyPropVal: v.text.trim(), keyValueOrder: j + 1};
                })};
        })};
        var data = new FormData();
        ['facultyId', 'facultyName', 'facultyDesignation', 'facultyOfficialEmail', 'facultyPersonalEmail', 'facultyMobNo']
            .forEach(function (id) { data.append(id, value(id)); });
        data.append('facultyDetails', JSON.stringify(details));
        var button = document.getElementById('btnSubmit');
        button.disabled = true;
        fetch('/uploadfile/editfacultydetails', {method: 'POST', body: data, credentials: 'same-origin'})
            .then(function (r) {
                return r.text().then(function (text) { return {ok: r.ok, status: r.status, text: text}; });
            })
            .then(function (r) {
                button.disabled = false;
                if (r.ok) {
                    dirty = false;
                    show('Saved. Opening the profile...', true);
                    window.location.href = '/facultyDetails?facultyId=' + encodeURIComponent(value('facultyId'));
                } else if (r.status === 403) {
                    show(r.text || 'You may only change your own profile.', false);
                } else {
                    show(r.text && r.text.length < 300 ? r.text : 'Saving failed (' + r.status + '). Please check the fields.', false);
                }
            })
            .catch(function () {
                button.disabled = false;
                show('Saving failed: no connection to the server.', false);
            });
    });

    window.addEventListener('beforeunload', function (e) {
        if (dirty) { e.preventDefault(); e.returnValue = ''; }
    });

    render();
})();
