'use strict'

window.onload = function() {

    const serverPath = 'http://localhost:8080/api';
    let loadInterval;
    let pageCounter = 1;

    const chatId = document.getElementById('chatId').value;

    document.getElementById('chatting').addEventListener('click', function () {
        document.getElementById('chat').hidden = false;
    });

    const fetchPlaces = async (page) => {
        const placesPath = `${serverPath}/messages/${chatId}?page=${page}`;
        const data = await fetch(placesPath, {cache: 'no-cache'});
        return data.json();
    };

    const messageTemplate = (listItem) => {
        const template = `<div class="message mr-3 my-2 col-8 mr-auto rounded-lg border-dark border">
                             <p><span><b>${listItem.userName}</b></span> - ${listItem.text} <span
                                         class="text-right text-muted">${listItem.time}</span></p>
                         </div>`;

        const elem = document.createElement('div');
        elem.innerHTML = template.trim();

        return elem.children[0];
    };

    async function loadNextMessage() {
        const data = await fetchPlaces(pageCounter);

        if (data.content.length === 0 || pageCounter === 20) {
            setPulling(3000)
            return;
        }

        pageCounter++;

        const list = document.getElementById('messages');
        for (let item of data.content) {
            list.append(messageTemplate(item));
            list.scrollTo(0, document.body.scrollHeight)
        }

        window.scrollTo(0, document.body.scrollHeight);
    }

    function setPulling(time) {
        if(loadInterval) {
            clearInterval(loadInterval);
        }

        loadInterval = setInterval(() => {
            loadNextMessage();
        }, time);
    }

    setPulling(1000);
}
