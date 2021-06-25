/**
 * 
 * @param {String} paramName 
 * @returns {}
 */
function getUrlParam(paramName) {

    let s_url = window.location.href;
    s_url = s_url.substring(s_url.indexOf("?"), s_url.length);
    let params = new URLSearchParams(s_url);

    if (params.has(paramName)) {
        return params.get(paramName);
    }
    return "";
}