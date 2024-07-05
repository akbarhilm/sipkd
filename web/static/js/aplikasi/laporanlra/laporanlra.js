$(document).ready(function() {
   laporan = "LRA"; // kondisi awal jenis laporan a/ LRA
   setComboSkpd();
   getIdInduk();
   //jenislaporan();
   setComboBulan();
});

var bulan;
var idinduk = 0;
var setIdAwal = 0;
var laporan;

function setbulan(nilaibulan){
    bulan = nilaibulan;
}

function cetaktes(){
    var idskpd ;
    if (setIdAwal == 0){
        idskpd = $('#idskpd').val();
        setIdAwal = 1;
    } else {
        idskpd = $('#pilihSkpd').val();
    }
    var tahun = $("#tahun").val();
    var bulanvar = tahun+$("#bulan").val();
    
    window.location.href= getbasepath() + "/laporanlra/json/prosescetaktes?tahun="+tahun+"&idskpd="+idskpd+"&bulan="+bulanvar;
    
}

function cetakjurnal() {
    var idskpd ;
    if (setIdAwal == 0){
        idskpd = $('#idskpd').val();
        setIdAwal = 1;
    } else {
        idskpd = $('#pilihSkpd').val();
    }
    var tahun = $("#tahun").val();
    var bulanvar = tahun+$("#bulan").val();
    var jenislaporan = $("#jenislap").val();
    
    console.log("jenislaporan cetak == "+jenislaporan);
    console.log("bulan yyyymm == "+bulanvar);
    console.log("bulan == "+$("#bulan").val());
    
    
    if (idskpd == "" || $("#bulan").val() == null){
        bootbox.alert("Data Tidak Tersedia");
    } else {
        window.location.href= getbasepath() + "/laporanlra/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&bulan="+bulanvar+"&jenislaporan="+jenislaporan;
    }
}

function getIdInduk() {
    var idskpd = $('#idskpd').val();
    
    $.getJSON(getbasepath() + "/laporanlra/json/getIdInduk", {idskpd: idskpd},
    function(result) {
        //console.log("id induk -- "+result);
        idinduk = result;
        
        jenislaporan(result);
    });
}

function getIdIndukOnChange() {
    var idskpd = $('#pilihSkpd').val();
    
    $.getJSON(getbasepath() + "/laporanlra/json/getIdInduk", {idskpd: idskpd},
    function(result) {
        //console.log("id induk -- "+result);
        idinduk = result;
        
        jenislaporan(result);
    });
}

function setComboSkpd() {
    var idskpd = $('#idskpd').val();
    
    $.getJSON(getbasepath() + "/laporanlra/json/setComboSkpd", { idskpd: idskpd},
    function(result) {
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
            
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['kodeSkpd'];
                 ket = result.aData[i]['ketSkpd'];
                
                 opt += '<option value="'+ kode + '" >' + ket + '</option>';
            }
        }
       
        $("#pilihSkpd").html(opt);
        
    });
    
}

function jenislaporan(id){
    $("#jenislap").html("");
    
    var idskpd = $("#idskpd").val();
    
    //option value 1 & 2 ada di provinsi
    var opt='<option value="3">1 - LRA Permendagri 13/2006</option>';
    opt += '<option value="4">2 - LRA PP 71/2010</option>';
    opt += '<option value="12">3 - Laporan Operasional SKPD</option>';
    opt += '<option value="14">4 - Laporan Perubahan Ekuitas SKPD</option>';
    opt += '<option value="7">5 - Neraca SKPD</option>';
    
    //console.log("id induk == "+idinduk);
    
    if (id == 1 ){
        opt += '<option value="5">6 - LRA Permendagri 13/2006 (Gabungan)</option>';
        opt += '<option value="6">7 - LRA PP 71/2010 (Gabungan)</option>';
        opt += '<option value="13">8 - Laporan Operasional SKPD (Gabungan)</option>';
        opt += '<option value="15">9 - Laporan Perubahan Ekuitas (Gabungan)</option>';
        opt += '<option value="8">10 - Neraca SKPD (Gabungan)</option>';
        
    }
    
    if (idskpd == 761 ){
        opt += '<option value="17">11 - Laporan Perubahan Ekuitas PPKD</option>';
        opt += '<option value="10">12 - Neraca PPKD</option>';
       
    }
    
    $("#jenislap").html(opt);
    
}

function setComboBulan() {
    var tahun = $("#tahun").val();
    var idskpd;
    if (setIdAwal == 0){
        idskpd = $('#idskpd').val();
        setIdAwal =1;
    } else {
        idskpd = $('#pilihSkpd').val();
    }
    
    var jenislap = $('#jenislap').val();
    
    if (jenislap == 3 || jenislap == 4 || jenislap == 5 || jenislap == 6){
        laporan = "LRA";
        
    } else if (jenislap == 12 || jenislap == 13){
        laporan = "LO";
        
    } else if (jenislap == 7 || jenislap == 8 || jenislap == 10){
        laporan = "NERACA";
        
    } else if (jenislap == 14 || jenislap == 15){
        laporan = "EKUITAS";
        
    } 
   
    $.getJSON(getbasepath() + "/laporanlra/json/setBulan", {tahun: tahun, idskpd: idskpd, laporan:laporan},
    function(result) {
        //console.log(result);
        
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['kodeBulan'];
                 ket = result.aData[i]['ketBulan'];
                
                 opt += '<option value="'+ kode + '" >' + ket + '</option>';
            }
        }
       
        $("#bulan").html(opt);
       
    });
  
}

