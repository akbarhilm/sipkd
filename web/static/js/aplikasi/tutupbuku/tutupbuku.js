$(document).ready(function() {
    $("#tanggal").datepicker("setDate", new Date()); // set sysdate untuk tanggal
    $("#saldoTunai").val(0);
    $("#saldoBank").val(0);
    $("#saldoLain").val(0);
    getNilaiKas();
    setTanggalTutup();
  
});

function getNilaiKas() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();
    
    console.log("bulan == "+bulan);
 
    $.getJSON(getbasepath() + "/tutupbuku/json/getnilaikas", {tahun: tahun, idskpd: idskpd, bulan: bulan},
    function (result) {
        
        var kasterimalalu = result.aData['kasTerimaLalu'];
        var kaskeluarlalu = result.aData['kasKeluarLalu'];
        var kasterima = result.aData['kasTerima'];
        var kaskeluar = result.aData['kasKeluar'];
        var kassaatini = result.aData['kasSaatIni'];
        
        $('#kasTerimaLalu').val(accounting.formatNumber(kasterimalalu));
        $('#kasKeluarLalu').val(accounting.formatNumber(kaskeluarlalu));
        $('#kasTerima').val(accounting.formatNumber(kasterima));
        $('#kasKeluar').val(accounting.formatNumber(kaskeluar));
        $('#kasSaatIni').val(accounting.formatNumber(kassaatini));
        
        //console.log("Kas Saat Ini"+kassaatini);
    });
}

function setTanggalTutup(){
    var tgl = $('#tanggal').val();
    var dd,mm,yy, tanggal;
    
    yy = tgl.substring(6);
    mm = tgl.substr(3, 2);
    dd = tgl.substr(0, 2);
    tanggal = yy+mm+dd;
    
   // console.log("tanggal tutup = "+tanggal);
    
    $('#tglPenutupan').val(tanggal);
    
}

function cetak(){
    var idskpd ;
    /*
    if (setIdAwal == 0){
        idskpd = $('#idskpd').val();
        setIdAwal = 1;
    } else {
        idskpd = $('#pilihSkpd').val();
    }
    var tahun = $("#tahun").val();
    var bulanvar = tahun+$("#bulan").val();
    
    window.location.href= getbasepath() + "/laporanlra/json/prosescetaktes?tahun="+tahun+"&idskpd="+idskpd+"&bulan="+bulanvar;
    */
}