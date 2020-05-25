<#import "partials/main.ftlh" as main/>

<@main.renderWith scripts=[]>

    <div class="container">
        <div class="cont-chats border border-rounded border-dark my-4">
            <#list chats>
                <table class="table table-hover text-left">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Created</th>
                    </tr>
                    </thead>
                    <#items as ch>
                        <tbody>
                        <tr onclick="location.href='/chat/${ch.id}?page=1'" style="cursor: pointer;">
                            <td>${ch.name}</td>
                            <td>${ch.time}</td>
                        </tr>
                        </tbody>
                    </#items>
                </table>
            </#list>
        </div>
        <#include "partials/pagination.ftlh">
    </div>


</@main.renderWith>