window.onload = () => {
    activateDeactivateNavBtns();
    adjustNoOfCards();
    const matchTypesSize = document.getElementById("matchTypesSize").innerText;
    const svgArrowRight = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-circle-fill svgArrow" viewBox="0 0 16 16">
                            <path d="M8 0a8 8 0 1 1 0 16A8 8 0 0 1 8 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
                        </svg>`;
    const svgArrowLeft = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">
                                 <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                                </svg>`;

    for (let i=0 ; i<matchTypesSize; i++){
        let matchCards = document.getElementsByClassName("matchCard"+i);
        let moreMatchesBtn = document.getElementById("ViewMoreMatches"+i);
        if(matchCards.length<=6){
            moreMatchesBtn.style.display = "none";
        }
        else{
            moreMatchesBtn.style.display = "";
            for (let j =  6; j<matchCards.length; j++ ) {
                matchCards[j].classList.remove("showCard");
                matchCards[j].classList.add("hideCard");
            }
        }
    }

    const cardHeaderLimit = 40;
    let cardHeader = document.getElementsByClassName("cardHeaderClass");
    for (let i=0; i<cardHeader.length; i++){
        if(cardHeader[i].innerText.length > 40) {
            cardHeader[i].innerText = cardHeader[i].innerText.substring(0, cardHeaderLimit) + "...";
        }
    }

    for (let i=0 ; i<matchTypesSize; i++){
        let moreMatchesBtn = document.getElementById("ViewMoreMatches"+i);
        let matchCards = document.getElementsByClassName("matchCard"+i);
        moreMatchesBtn.addEventListener('click', (event) => {
            let hideCardCount = 0;
            for (let j =  0; j<matchCards.length; j++ ) {
                if(matchCards[j].classList.contains("hideCard")){
                    matchCards[j].classList.remove("hideCard");
                    matchCards[j].classList.add("showCard");
                    hideCardCount ++;
                }
                if(hideCardCount >= 6){
                    break;
                }
            }
            hideCardCount = 0;
            for (let j =  0; j<matchCards.length; j++ ) {
                if (matchCards[j].classList.contains("hideCard")) {
                    hideCardCount ++;
                }
            }
            let moreMatches = false;
            if(moreMatchesBtn.innerText.trim() === "Less Matches"){
                for (let j =  6; j<matchCards.length; j++ ) {
                    matchCards[j].classList.remove("showCard");
                    matchCards[j].classList.add("hideCard");
                }
                moreMatchesBtn.innerHTML="More Matches    "+svgArrowRight;
                moreMatches = true;
            }

            if(hideCardCount === 0 && !moreMatches){
                moreMatchesBtn.innerHTML="Less Matches    "+svgArrowLeft;
            }
        })
    }

}

function activateDeactivateNavBtns(){
    let live = document.getElementById("live");
    let recent = document.getElementById("recent");
    let upcoming = document.getElementById("upcoming");
    let currentUrl = window.location.href;
    const currentEndpoint = currentUrl.split("/")[5];
    if (currentEndpoint === "live"){
        live.classList.add("activateNavBtn");
        live.classList.remove("deactivateNavBtn")

        recent.classList.remove("activateNavBtn");
        recent.classList.add("deactivateNavBtn")

        upcoming.classList.remove("activateNavBtn");
        upcoming.classList.add("deactivateNavBtn")
    }
    else if(currentEndpoint === "recent"){
        recent.classList.add("activateNavBtn");
        recent.classList.remove("deactivateNavBtn")

        live.classList.remove("activateNavBtn");
        live.classList.add("deactivateNavBtn")

        upcoming.classList.remove("activateNavBtn");
        upcoming.classList.add("deactivateNavBtn")
    }
    else if(currentEndpoint === "upcoming"){
        upcoming.classList.add("activateNavBtn");
        upcoming.classList.remove("deactivateNavBtn")

        recent.classList.remove("activateNavBtn");
        recent.classList.add("deactivateNavBtn")

        live.classList.remove("activateNavBtn");
        live.classList.add("deactivateNavBtn")
    }
}

window.onresize = () => { adjustNoOfCards(); }

function adjustNoOfCards() {
    let width = screen.width;
    let matchesCards = document.getElementsByClassName("matchesCards");
    if(width>1200){
        for (let i=0; i<matchesCards.length; i++){
            if(!matchesCards[i].classList.contains("g-col-6") ) {
                matchesCards[i].classList.add("g-col-6");
            }
            if(!matchesCards[i].classList.contains("g-col-md-4")){
                matchesCards[i].classList.add("g-col-md-4");
            }
            if(matchesCards[i].classList.contains("g-col-md-6")) {
                matchesCards[i].classList.remove("g-col-md-6");
            }
        }
    }
    else if (width>1000 && width<=1200){
        for (let i=0; i<matchesCards.length; i++){
            if(matchesCards[i].classList.contains("g-col-md-4")) {
                matchesCards[i].classList.remove("g-col-md-4");
            }
            if(matchesCards[i].classList.contains("g-col-12")) {
                matchesCards[i].classList.remove("g-col-12");
            }
            if(!matchesCards[i].classList.contains("g-col-6") ) {
                matchesCards[i].classList.add("g-col-6");
            }
        }
    }
    else if (width<=1000){
        for (let i=0; i<matchesCards.length; i++){
            if(matchesCards[i].classList.contains("g-col-md-4")) {
                matchesCards[i].classList.remove("g-col-md-4");
            }
            if(matchesCards[i].classList.contains("g-col-6") ) {
                matchesCards[i].classList.remove("g-col-6");
            }
            if(!matchesCards[i].classList.contains("g-col-12") ) {
                matchesCards[i].classList.add("g-col-12")
            }
        }
    }
}
