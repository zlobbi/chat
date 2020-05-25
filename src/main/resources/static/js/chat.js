'use strict'


const serverPath = 'http://localhost:8080/api';
let index = 0;

const getCurrentPage = () => {
    index = index + 1;
    return index - 1;
};

const chatId = document.getElementById('chatId').value;

document.getElementById('loadNext').innerText = 'load';

document.getElementById('chatting').addEventListener('click', function () {
    document.getElementById('chat').hidden = false;
});


(function loadPlacesPageable() {
    const placeTemplate = (listItem) => {
        const template = `<div class="message mr-3 my-2 col-8 mr-auto rounded-lg border-dark border">
                             <p><span><b>${listItem.userName}</b></span> - ${listItem.text} <span
                                         class="text-right text-muted">${listItem.time}</span></p>
                         </div>`;

        const elem = document.createElement('div');
        elem.innerHTML = template.trim();

        // return inner div with classes flex etc
        return elem.children[0];
    };

    const fetchPlaces = async (page) => {
        const placesPath = `${serverPath}/messages/?page=${page}`;
        const data = await fetch(placesPath, {cache: 'no-cache'});
        return data.json();
    };

    const loadNextPlacesGenerator = (loadNextElement, page) => {
        return async (event) => {
            event.preventDefault();
            event.stopPropagation();

            const data = await fetchPlaces(page);

            loadNextElement.hidden = true;
            if (data.length === 0) {
                return;
            }

            const list = document.getElementById('messages');
            for (let item of data) {
                list.append(placeTemplate(item));
                setTimeout(clickNext, 1000);
                list.scrollTo(0, document.body.scrollHeight)
            }

            loadNextElement.addEventListener('click', loadNextPlacesGenerator(loadNextElement, page + 1), {once: true});
            window.scrollTo(0, document.body.scrollHeight);
        };
    };
    document.getElementById('loadPrev').hidden = true;
    const loadNextElement = document.getElementById('loadNext');
    if (loadNextElement !== null) {
        loadNextElement.addEventListener('click', loadNextPlacesGenerator(loadNextElement, getCurrentPage()), {once: true});
        clickNext();
    }

    function clickNext() {
        document.getElementById('loadNext').click();
    }

})();

