$(document).ready(function() {
    laporan = "LRA"; // kondisi awal jenis laporan a/ LRA

    jenislaporan(0);
   
   if ($("#idskpdpop").val() !== ""){
       setComboBulan();
   }
   
});

var bulan;
var idinduk;
var laporan;

function setbulan(nilaibulan){
    bulan = nilaibulan;
}

function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var bulanvar = tahun+$("#bulan").val();
    var jenislaporan = $("#jenislap").val();
    
    console.log("jenislaporan == "+jenislaporan);
    
    if (idskpd == ""){
        bootbox.alert("Pilih SKPD Terlebih Dulu");
    } else if ($("#bulan").val() == null){
        bootbox.alert("Data Tidak Tersedia");
    } else {
        window.location.href= getbasepath() + "/laporanlra/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&bulan="+bulanvar+"&jenislaporan="+jenislaporan;
    }
  
}

function getIdInduk() {
    var idskpd = $('#idskpdpop').val();
    
    $.getJSON(getbasepath() + "/laporanlra/json/getIdInduk", {idskpd: idskpd},
    function(result) {
        //console.log("id induk -- "+result);
        idinduk = result;
        
        jenislaporan(result);
        
        setComboBulan();
    });
}

function jenislaporan(id){
    $("#jenislap").html("");
    
    var idskpd = $('#idskpdpop').val();
    console.log("jenislaporan() id == "+ id);
    
    //console.log("id skpd -- "+idskpd);
    var opt='<option value="1">1 - LRA Permendagri 13/2006 Provinsi</option>';
    opt +='<option value="2">2 - LRA PP 71/2010 Provinsi</option>';
    opt +='<option value="11">3 - Laporan Operasional Provinsi</option>';
    opt += '<option value="16">4 - Laporan Perubahan Ekuitas Provinsi</option>';
    opt +='<option value="9">5 - Neraca Provinsi</option>';
    opt +='<option value="18">6 - Laporan Perubahan SAL</option>';
    
    if (idskpd !== ""){
        opt +='<option value="3">7 - LRA Permendagri 13/2006</option>';
        opt += '<option value="4">8 - LRA PP 71/2010</option>';
        opt += '<option value="12">9 - Laporan Operasional SKPD</option>';
        opt += '<option value="14">10 - Laporan Perubahan Ekuitas SKPD</option>';
        opt += '<option value="7">11 - Neraca SKPD</option>';

        if (id == 1 ){
            opt += '<option value="5">12 - LRA Permendagri 13/2006 (Gabungan)</option>';
            opt += '<option value="6">13 - LRA PP 71/2010 (Gabungan)</option>';
            opt += '<option value="13">14 - Laporan Operasional SKPD (Gabungan)</option>';
            opt += '<option value="15">15 - Laporan Perubahan Ekuitas (Gabungan)</option>';
            opt += '<option value="8">16 - Neraca SKPD (Gabungan)</option>';
        }
        
        if (idskpd == 761){
            opt += '<option value="17">17 - Laporan Perubahan Ekuitas PPKD</option>';
            opt += '<option value="10">18 - Neraca PPKD</option>';
        }
        
    }
    
    $("#jenislap").html(opt);
    
}

function setComboBulan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var jenislap = $('#jenislap').val();
    
    if (jenislap == 1 || jenislap == 2 || jenislap == 3 || jenislap == 4 || jenislap == 5 || jenislap == 6){
        laporan = "LRA";
        
    } else if (jenislap == 11 || jenislap == 12 || jenislap == 13){
        laporan = "LO";
        
    } else if (jenislap == 7 || jenislap == 8 || jenislap == 9 || jenislap == 10){
        laporan = "NERACA";
        
    } else if (jenislap == 14 || jenislap == 15 || jenislap == 16){
        laporan = "EKUITAS";
        
    } else if (jenislap == 17){
        laporan = "SAL";
        
    } 
   
    console.log("jenis laporan = "+laporan);
    
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