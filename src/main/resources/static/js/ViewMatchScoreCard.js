window.onload = () => {
    activateDeactivateNavBtns();

    let width = screen.width;
    let scoreCardDef =  document.getElementsByClassName('scoreCardDef');
    if(width<1000) {

        for (let i = 0; i < scoreCardDef.length; i++) {
            scoreCardDef[i].classList.remove("scoreCardPc");
            scoreCardDef[i].classList.add("scoreCardMobile");
        }

        let outdesc = document.getElementsByClassName("outdesc");
        for (let i=0; i<outdesc.length; i++){
            outdesc[i].classList.remove("col-4");
            outdesc[i].classList.add("col-3")
        }

    }
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