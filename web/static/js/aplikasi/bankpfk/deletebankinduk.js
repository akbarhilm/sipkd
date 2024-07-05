$(document).ready(function() {
    if ($("#kodeAktif").val() == 1) {
        document.getElementById("kodeaktif").checked = true;
    } else {
        document.getElementById("kodeaktif").checked = false;
    }
});

function hapus() {

    var idbank = $('#idBank').val();

    bootbox.confirm("Apakan Anda Yakin Akan Menghapus Data " + $("#namaBankTransfer").val() + " ?", function(result) {
        if (result) {

            return   $.ajax({
                type: "POST",
                url: getbasepath() + "/bankpfk/json/prosesdelete",
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idbank": idbank})

            }).always(function(data) {
                bootbox.alert("Data Bank Berhasil Dihapus");
                window.location.href = getbasepath() + "/bankpfk/indexbankpfk";
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });

}

