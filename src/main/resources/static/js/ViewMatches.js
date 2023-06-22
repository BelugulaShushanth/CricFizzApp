window.onload = () => {
    const matchTypesSize = document.getElementById("matchTypesSize").innerText;
    const svgArrow = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-circle-fill svgArrow" viewBox="0 0 16 16">
                            <path d="M8 0a8 8 0 1 1 0 16A8 8 0 0 1 8 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
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
                moreMatchesBtn.innerHTML="More Matches    "+svgArrow;
                moreMatches = true;
            }

            if(hideCardCount === 0 && !moreMatches){
                moreMatchesBtn.innerHTML="Less Matches    "+svgArrow;
            }
        })
    }

}