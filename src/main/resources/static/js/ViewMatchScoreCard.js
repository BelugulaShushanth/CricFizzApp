window.onload = () => {
    activateDeactivateNavBtns();
    //addRoutingToMatchScoreCard();
}
function activateDeactivateNavBtns(){
    let live = document.getElementById("live");
    let recent = document.getElementById("recent");
    let upcoming = document.getElementById("upcoming");
    live.classList.remove("activateNavBtn");
    live.classList.add("deactivateNavBtn")

    recent.classList.remove("activateNavBtn");
    recent.classList.add("deactivateNavBtn")

    upcoming.classList.remove("activateNavBtn");
    upcoming.classList.add("deactivateNavBtn");
}
// function addRoutingTo(){
//     let currentUrl = window.location.href;
//
// }