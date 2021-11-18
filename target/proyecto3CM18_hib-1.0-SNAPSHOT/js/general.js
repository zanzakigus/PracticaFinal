function clean_navbar() {
    $("#navbar a").each(function () {
        $(this).removeClass("active");

    });
}

function navbar_update(numero) {
    iter = 0;
    $(".navbar a").each(function () {
        if (iter === numero) {
            $(this).addClass("active");
        }
        iter++;
    });
}


