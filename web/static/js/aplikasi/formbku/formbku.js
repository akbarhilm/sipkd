$(document).ready(function() {
    document.getElementById("labelakunbelanja").style.display = "none";
    setBulan();
    setSaldoAwal();
    //setWilayah();
    //setDisplayWilayah();
    
});

function setDisplayWilayah(){
    if ($("#idskpd").val() == 761) {
        document.getElementById("labelwilayah").style.display = "block";
    } else {
        document.getElementById("labelwilayah").style.display = "none";
    }
}

function cetak() {
    var idskpd = $('#idskpd').val();
    var bulan = $("#bulan").val();
    var tahun = $("#tahun").val();
    var jenislaporan = $("#jenislap").val();
    var saldo = $("#saldo").val();
    var akun = $("#akunbelanja").val();
    var wilayah = $("#wilayah").val();
    
    if (saldo == "") {
        saldo = "0";
    }

    if (idskpd == "") {
        bootbox.alert("Data Tidak Tersedia");
    } else {
        window.location.href = getbasepath() + "/formbku/json/prosescetak?tahun=" + tahun + "&idskpd=" + idskpd + "&bulan=" + bulan + "&jenislaporan=" + jenislaporan + "&saldo=" + saldo + "&akun=" + akun + "&wilayah=" + wilayah;
    }
}

function setIdskpd(id) {
    //console.log("idskpd = "+id);
    $('#idskpd').val(id);
    setSaldoAwal();
}

function setSaldoAwal() {
    var bulan = $('#bulan').val()
    var idskpd = $('#idskpd').val();
    var jenis = $('#jenislap').val();

    if (jenis == "7" || jenis == "8" || jenis == "9" || jenis == "P1" || jenis == "P2" || jenis == "P3" || jenis == "P4" || jenis == "P5" || jenis == "P6") {
        $.getJSON(getbasepath() + "/formbku/json/getSaldoAwal", {jenis: jenis, bulan: bulan, idskpd: idskpd},
        function(result) {

            var saldo = result.aData[0]['saldoawal'];
            console.log("saldo = " + saldo);
            $("#saldo").val(saldo);
            document.getElementById("saldo").readOnly=true;
        });
    } else {
        document.getElementById("saldo").readOnly=false;
        $("#saldo").val("0");
    }

}

function setAkunBelanja() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();

    $.getJSON(getbasepath() + "/formbku/json/getAkunBelanja", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['kodeakun'];
                ket = result.aData[i]['namaakun'];

                opt += '<option value="' + kode + '" >' + ket + '</option>';
                
            }
            $("#akunbelanja").html(opt);
            
        }

    });
}

function setPanelAkun(){
    
    if($('#jenislap').val() == 27){
        document.getElementById("labelakunbelanja").style.display = "block";
        setAkunBelanja();
    } else {
        document.getElementById("labelakunbelanja").style.display = "none";
    }
    
}

function setWilayah() {
    $.getJSON(getbasepath() + "/formbku/json/getWilayah", {},
    function(result) {
        var banyak, kode, ket;
        var opt = "";
        banyak = result.aData.length;

        for (var i = 0; i < banyak; i++) {
            kode = result.aData[i]['kodeWilayah'];
            ket = result.aData[i]['ketWilayah'];

            opt += '<option value="' + kode + '" >' + ket + '</option>';
            
        }
        $("#wilayah").html(opt);
    });
}


function setBulan() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    
    $.getJSON(getbasepath() + "/formbku/json/setBulan", {tahun: tahun, idskpd: idskpd},
    function(result) {
        //console.log(result);
        
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['kodeBulan'];
                 ket = result.aData[i]['bulan'];
                
                 opt += '<option value="'+ kode + '">' + ket + '</option>';
            }
            $('#btncetak').attr("disabled", false);
        } else {
            $('#btncetak').attr("disabled", true);
        }
       
        $("#bulan").html(opt);
        
    });
    
}