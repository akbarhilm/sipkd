
$(document).ready(function() {
    //document.getElementById("labelkegiatan").style.display = "none"; 
    //getBebanSetorUP();
    setkegiatan();
});

function setkegiatan() {
    var kodebeban = $('#beban').val();

    if (kodebeban == 'UP') {
        document.getElementById("labelkegiatan").style.display = "none";
    } else if (kodebeban == 'TU') {
        document.getElementById("labelkegiatan").style.display = "block";
    }
}

function nilaisisa(nilaisisaUP, nilaisisaTU) {
    var kodebeban = $('#beban').val();
    var nilai = "";

    if (kodebeban == 'UP') {
        nilai = nilaisisaUP;

    }
    else if (kodebeban == 'TU') {
        nilai = 0;
        setNilaiTU();
        //nilai=nilaisisaTU;
    }

    $("#nilaiSetor").val(nilai);
    cekNilai(nilai);

}

function cekNilai(nilai) {
    //console.log("nilai == "+nilai);
    
    var sisaup = $("#sisaup").val();
    
    if (nilai < 0) {
        $('#buttoninduk').attr("disabled", true); // btn simpan
    } else {
        $('#buttoninduk').attr("disabled", false); // btn simpan aktif
    }
    
     if (sisaup < nilai){
         bootbox.alert("Nilai Setoran Tidak Boleh Lebih Besar dari Sisa Kas UP");
        $("#nilaiSetor").val(sisaup);
    }
    
}

function setNilaiTU() {
    $.getJSON(getbasepath() + "/setor/json/getSisaTU", {idskpd: $("#idskpd").val(), idkegiatan: $("#idKegiatan").val()},
    function(result) {
        var nilai;

        nilai = result.aData;
        $("#nilaiSetor").val(nilai);
        cekNilai(nilai);
    });
}

function getBebanSetorUP() {
    $.getJSON(getbasepath() + "/setor/json/getBebanSetorUP", {idskpd: $("#idskpd").val()},
    function(result) {

        var banyak = result.aData;
        var opt = "";
        console.log("getBebanSetorUP banyak == " + banyak);
        if (banyak > 0) {
            opt = '<option value="TU">TU</option>';
            
        } else {
            opt = '<option value="UP">UP/GU</option>';
            opt += '<option value="TU">TU</option>';

        }


        $("#beban").html(opt);
    });
}

