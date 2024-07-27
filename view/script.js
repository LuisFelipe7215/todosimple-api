const url = "http://localhost:8080/task/user/1";

function hideLoader() {
    document.getElementById("loading").style.display = "none";
}

function show(tasks) {
    let tab = `<tbody>`;

    for (let task of tasks) {
        tab += `
            <tr>
                <td scope="row">${task.id}</td>
                <td>${task.description}</td>
                <td>${task.user.username}</td>
                <td>${task.user.id}</td>
            </tr>
        `;
    }

    tab += `</tbody>`;

    document.getElementById("tasks").innerHTML = tab;
}

async function getAPI(url) {
    const response = await fetch(url, { method: "GET" });

    var data = await response.json();
    console.log(data);
    if (response)
        hideLoader();
    show(data);
}

getAPI(url);