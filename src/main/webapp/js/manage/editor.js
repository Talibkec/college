/*
 * The visual editor of /manage/sections and /manage/blocks (Quill 2). Alignment, font size and colours are written as
 * inline styles (text-align, font-size, color, background-color), which the server's sanitiser keeps and the public
 * pages show without Quill's stylesheet.
 */
var KecEditor = (function () {
    var SIZES = ['12px', '14px', false, '18px', '20px', '24px', '32px'];
    var registered = false;

    function register() {
        if (registered) return;
        Quill.register(Quill.import('attributors/style/align'), true);
        var size = Quill.import('attributors/style/size');
        size.whitelist = SIZES.filter(function (s) { return s; });
        Quill.register(size, true);
        // labels of the size picker (Quill's stylesheet only knows small/large/huge)
        var css = '';
        size.whitelist.forEach(function (s) {
            css += '.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="' + s + '"]::before,'
                + '.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="' + s + '"]::before{content:"' + s + '"}';
        });
        var style = document.createElement('style');
        style.textContent = css;
        document.head.appendChild(style);
        registered = true;
    }

    /** Quill's own link button does nothing without selected text: ask for the address (and the text) instead. */
    function linkHandler(quill) {
        return function (value) {
            var range = quill.getSelection(true);
            if (!value) { quill.format('link', false, 'user'); return; }
            var url = window.prompt('Link address (https://..., mailto:... or a page of this website such as /about/history)', 'https://');
            if (!url || url === 'https://') return;
            url = url.trim();
            if (range.length === 0) {
                var text = window.prompt('Link text', url);
                if (text === null) return;
                text = text || url;
                quill.insertText(range.index, text, 'link', url, 'user');
                quill.setSelection(range.index + text.length, 0, 'user');
            } else {
                quill.format('link', url, 'user');
            }
        };
    }

    /** headers: the heading levels offered, e.g. [2, 3, 4]. */
    function create(element, headers) {
        register();
        var quill = new Quill(element, {theme: 'snow', modules: {toolbar: [
            [{header: headers.concat([false])}, {size: SIZES}],
            ['bold', 'italic', 'underline', 'strike'],
            [{color: []}, {background: []}],
            [{align: []}],
            [{list: 'ordered'}, {list: 'bullet'}],
            ['link', 'blockquote'],
            ['clean']
        ]}});
        quill.getModule('toolbar').addHandler('link', linkHandler(quill));
        return quill;
    }

    /**
     * The editor's HTML for saving. Quill 2 keeps every list as <ol> with <li data-list="bullet|ordered"> plus helper
     * spans; turn that back into plain <ul>/<ol> lists without the helpers.
     */
    function html(quill) {
        var root = quill.root.cloneNode(true);
        root.querySelectorAll('.ql-ui, .ql-cursor').forEach(function (el) { el.parentNode.removeChild(el); });
        root.querySelectorAll('ol').forEach(function (ol) {
            var list = null, type = null;
            Array.prototype.slice.call(ol.children).forEach(function (li) {
                var t = li.getAttribute('data-list') === 'bullet' ? 'ul' : 'ol';
                if (t !== type) {
                    list = document.createElement(t);
                    ol.parentNode.insertBefore(list, ol);
                    type = t;
                }
                li.removeAttribute('data-list');
                list.appendChild(li);
            });
            ol.parentNode.removeChild(ol);
        });
        return root.innerHTML;
    }

    return {create: create, html: html};
})();
