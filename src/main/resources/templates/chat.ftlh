<#import "partials/main.ftlh" as main/>

<@main.renderWith scripts=["/js/chat.js"]>

    <div class="container">
        <div class="chat row my-4 border border-dark rounded-lg">
            <div class="bg-dark col-12 text-light mb-4 text-center">
                <input id="chatId" value="${chat.id}" hidden>
                <b class="my-3" style="font-size: 16px; text-transform: uppercase">${chat.name} ${chat.time}</b>
            </div>
            <div id="messages" class="col-12 overflow-auto" style="max-height: 300px; min-height: 200px">

            </div>
            <#include "partials/pagination.ftlh">
            <div class="bg-dark col-12 text-light mt-4 p-2 text-center text-upercease navbar-brand">
                <a class="btn-su px-2 m-1" id="chatting">chatting</a>
            </div>
        </div>
        <div class="chat row my-4 border border-dark rounded-lg" id="chat" hidden>
            <div class="bg-dark col-12 text-light mb-4 text-center text-upercease navbar-brand">
                <b class="">message</b>
            </div>
            <form class="col-8 mx-auto border border-dark rounded-lg" method="post">
                <input name="chatId" value="${chat.id}" hidden>
                <div class="form-group mt-4 border border-dark rounded-lg">
                    <#if dto??>
                        <p>${dto.name}</p>
                    <#else >
                        <input name="userName" class="form-control" value="${form.userName}"
                               placeholder="Input username">
                    </#if>
                </div>
                <div class="form-group border border-dark rounded-lg">
                    <textarea name="text" class="form-control" rows="3">${form.text}</textarea>
                </div>
                <div class="form-group">
                    <div class="col-12 text-light text-right navbar-brand">
                        <button type="submit" class="btn-su border rounded-lg">send message</button>
                    </div>
                </div>
            </form>
            <div class="bg-dark col-12 text-light mt-4 py-4 text-center text-upercease navbar-brand">
            </div>
        </div>
    </div>

</@main.renderWith>