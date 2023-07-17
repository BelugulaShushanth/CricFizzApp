window.onload = () => {
    let subAlerts = document.getElementsByClassName("subAlerts")[0];
    let viewAlerts = document.getElementsByClassName("viewAlerts")[0];

    subAlerts.addEventListener('click', () => {
        subAlerts.classList.remove("inActive");
        subAlerts.classList.add("active");

        viewAlerts.classList.remove("active");
        viewAlerts.classList.add("inActive");
    })

    viewAlerts.addEventListener('click', () => {
        viewAlerts.classList.remove("inActive");
        viewAlerts.classList.add("active");

        subAlerts.classList.remove("active");
        subAlerts.classList.add("inActive");
    })

    const currentUrl = window.location.href;
    const url = currentUrl.split("/");
    const paramsDataUrl = url[0]+"//"+url[2] + "/alerts/getAlertParamsData"

    let matchType = document.getElementById('matchType');
    matchType.addEventListener('change', () => {
        window.location.href  = paramsDataUrl + "?matchType="+matchType.value;
    })

    let seriesName = document.getElementById('seriesName');
    seriesName.addEventListener('change', () => {
        window.location.href = paramsDataUrl + "?matchType="+matchType.value+"&seriesId="+seriesName.value;
    })
}