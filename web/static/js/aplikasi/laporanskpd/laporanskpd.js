$(document).ready(function() {
    setpanel(1);
});

var jenislaporan;

function cetakjurnal() {
    console.log("jenis laporan == "+jenislaporan);
    if (jenislaporan == 1){ // Jurnal Umum SKPD By No Jurnal
        var aaa = $("#tahun").val();
        var ccc = $("#nojurpop").val();
        var eee = aaa+"-"+"JURNAL-UMUM-SKPD"+"-"+ccc+".pdf";

        if (ccc == ""){
            bootbox.alert("Pilih Nomor Jurnal Terlebih Dulu");
        } else {
            window.location.href= getbasepath() + "/laporanskpd/json/prosescetak?tahun="+aaa+"&nojur="+ccc+"&namafile="+eee+"&jenislaporan="+jenislaporan;
        }
   
    } else if (jenislaporan == 2){ //Jurnal Umum SKPD By Akun
        var tgl = $("#tglPosting").val();
        var yy = tgl.substring(6);
        var mm = tgl.substr(3, 2);
        var dd = tgl.substr(0, 2);

        var tglpost = yy+mm+dd;
        var tahun = $("#tahun").val();
        var idskpd = $("#idskpd").val();
        var idbas = $("#idbaspop").val();
        var eee = tahun+"-"+"JURNAL-UMUM-SKPD"+"-"+idskpd+"-"+tglpost+".pdf";

        if (tgl == ""){
            bootbox.alert("Tanggal Posting Harus Diisi");
        }else if (idskpd == ""){
            bootbox.alert("SKPD Harus Diisi");
        }else{
            window.location.href= getbasepath() + "/laporanskpd/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&namafile="+eee+"&tglpost="+tglpost+"&idbas="+idbas+"&jenislaporan="+jenislaporan;
        }
       
    } else if (jenislaporan == 3){ // Jurnal SKPD
        var aaa = $("#tahun").val();
        var ccc = $("#nojurpop").val();
        var eee = aaa+"-"+"JURNAL-SKPD"+"-"+ccc+".pdf";
        
        if (ccc == ""){
            bootbox.alert("Pilih Nomor Jurnal Terlebih Dulu");
        } else {
            window.location.href= getbasepath() + "/laporanskpd/json/prosescetak?tahun="+aaa+"&nojur="+ccc+"&namafile="+eee+"&jenislaporan="+jenislaporan;
        }
        
    } else if (jenislaporan == 4){ // LRA Permendagri 13
        var aaa = $("#tahun").val();
        var eee = aaa+"-"+"LRA-Permendagri-13"+".pdf";
        
        window.location.href= getbasepath() + "/laporanskpd/json/prosescetak?tahun="+aaa+"&namafile="+eee+"&jenislaporan="+jenislaporan;
    
    } else if (jenislaporan == 5){ // LRA PP 71
        var aaa = $("#tahun").val();
        var eee = aaa+"-"+"LRA-PP-71"+".pdf";
        
        window.location.href= getbasepath() + "/laporanskpd/json/prosescetak?tahun="+aaa+"&namafile="+eee+"&jenislaporan="+jenislaporan;

    }
}

function setpanel(jenis){
    
    jenislaporan =jenis;
    var a = document.getElementById("pilihnojur");
    
    if (jenislaporan == 1){
        a.href = getbasepath() + "/nojurnal/listnojurnal?target='_top'";
    } else if (jenislaporan == 3){
        a.href = getbasepath() + "/nojurnal/listnojurnalskpd?target='_top'";
    }
    
    if (jenis == 1){ // Jurnal Umum SKPD By No Jurnal
        //disbale
        document.getElementById("tglPosting").readOnly = true;
        document.getElementById("akunpilih").style.visibility = "hidden";
        $("#idbaspop").val("");
        $("#akunketpop").val("");
        
        //enable
        document.getElementById("pilihnojur").style.visibility = "visible";
        
    } else if (jenis == 2){ //Jurnal Umum SKPD By Akun
        //enable 
        document.getElementById("tglPosting").readOnly = false;
        document.getElementById("akunpilih").style.visibility = "visible";
        
        //disbale
        document.getElementById("pilihnojur").style.visibility = "hidden";
        $("#nojurpop").val("");
        
    } else if (jenis == 3){ // Jurnal SKPD
         //disbale
        document.getElementById("tglPosting").readOnly = true;
        document.getElementById("akunpilih").style.visibility = "hidden";
        $("#idbaspop").val("");
        $("#akunketpop").val("");
        
        //enable
        document.getElementById("pilihnojur").style.visibility = "visible";
        
    } else if (jenis == 4 || jenis == 5){ // LRA Permendagri 13 || LRA PP 71
         //disbale
        document.getElementById("tglPosting").readOnly = true;
        document.getElementById("akunpilih").style.visibility = "hidden";
        $("#idbaspop").val("");
        $("#akunketpop").val("");
        
        document.getElementById("pilihnojur").style.visibility = "hidden";
        $("#nojurpop").val("");
        
    } 
    
}

function tampilCek(){
    var a = document.getElementById("pilihnojur");
    
    if (jenislaporan == 1){
        a.href = getbasepath() + "/nojurnal/listnojurnal?target='_top'";
    } else if (jenislaporan == 3){
        a.href = getbasepath() + "/nojurnal/listnojurnalskpd?target='_top'";
    }
}

