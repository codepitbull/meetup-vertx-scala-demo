var eb = new EventBus('http://localhost:8668/eventbus');
eb.onopen = function() {
    eb.registerHandler("browser", function (error, message) {
        console.log("received " + JSON.stringify(message));
    });
    eb.send("server", "whups");
};