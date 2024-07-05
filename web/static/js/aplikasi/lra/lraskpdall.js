$(document).ready(function() {
    setjenis(1);
    bulan = "01";
    getkodestatus(); 
});

var jenisLRA;
var kodestatus;
var bulan;

function setbulan(nilaibulan){
    bulan = nilaibulan;
}

function simpan() {
    var dpaperubahan;
    
    if (document.getElementById("dpaperubahan").checked == true){
        dpaperubahan="1";
    } else if (document.getElementById("dpaperubahan").checked == false){
        dpaperubahan="0";
    }
    
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    
    
    if (idskpd == "" && jenisLRA == 2){
        bootbox.alert("SKPD Tidak Boleh Kosong");
    } else if (kodestatus == "1"){
        bootbox.alert("Dara LRA Sudah Pernah Diproses.");
    } else{
        
        var varurl = getbasepath() + "/lra/json/prosessimpan";
        var dataac = [];

        var datajour = {
            tahun : tahun,
            idskpd : idskpd,
            bulan : bulan,
            perubahan : dpaperubahan
        }
        dataac = datajour;

        return   $.ajax({
            type: "POST",
            url: varurl,
            contentType: "text/plain; charset=utf-8",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(dataac)
        }).always(function(data) {
            //grid();
            bootbox.alert("Proses LRA SKPD Berhasil Disimpan");
        }); 
    }
}

function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
      
    var eee = tahun+"-"+"JURNAL-NERACA-SKPD"+"-"+idskpd+".pdf";
    window.location.href= getbasepath() + "/lra/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&namafile="+eee;
}

function setjenis(jenis){
    jenisLRA = jenis;
    
    if (jenis == 1){ // Provinsi
        //disbale
        document.getElementById("pilihskpd").style.visibility = "hidden";
        $("#idskpdpop").val("");
        $("#skpdpop").val("");
        
    } else if (jenis == 2){ //SKPD
        //enable 
        document.getElementById("pilihskpd").style.visibility = "visible";
    }
}

function getkodestatus() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    
    var tglgabung = tahun+bulan;
    
    if(jenisLRA == 1){
        
        $.getJSON(getbasepath() + "/lra/json/getKodeStatusProvinsi", { tanggal:tglgabung},
        function(result) {
            if (result == null){
                kodestatus = "0";
            } else {
                kodestatus = result;
            }
            
        });
        
    } else if (jenisLRA == 2){
        if (idskpd !== "" && bulan !== ""){
            $.getJSON(getbasepath() + "/lra/json/getKodeStatus", {idskpd: idskpd, tanggal:tglgabung},
            function(result) {
                if (result == null){
                kodestatus = "0";
            } else {
                kodestatus = result;
            }
                //kodestatus = result;
            });
        }
    }
    
}

function cek(){
    getkodestatus();
    bootbox.alert("Kode Status == "+ kodestatus);
    //bootbox.alert("ID SKPD = " + idskpd + " || Kode SKPD = " + kodeskpd + " || Nama SKPD = " + namaskpd);
}
