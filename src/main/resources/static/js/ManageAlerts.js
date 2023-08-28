window.onload = () => {
    let subAlerts = document.getElementsByClassName("subAlerts")[0];
    let viewAlerts = document.getElementsByClassName("viewAlerts")[0];

    const currentUrl = window.location.href;
    const url = currentUrl.split("/");

    subAlerts.addEventListener('click', () => {
        window.location.href  =  url[0]+"//"+url[2]  + "/alerts/dashboard";
        subAlerts.classList.remove("inActive");
        subAlerts.classList.add("active");

        viewAlerts.classList.remove("active");
        viewAlerts.classList.add("inActive");

    })

    viewAlerts.addEventListener('click', () => {
        window.location.href  =  url[0]+"//"+url[2]  + "/alerts/viewAlerts";
        viewAlerts.classList.remove("inActive");
        viewAlerts.classList.add("active");

        subAlerts.classList.remove("active");
        subAlerts.classList.add("inActive");
    })


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