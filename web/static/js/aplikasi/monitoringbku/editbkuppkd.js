$(document).ready(function() {
    //getBulanBySaldoAwal();//getKodeTutup();
    unformatnilai();
});

var banyakTutupMax, kodeTutupMax, bulanTutupMax;

function unformatnilai() {
    var nilai;
    //var nilaiinput = accounting.unformat($("#nilaiinput" + index).val(), ",");
    
    console.log("nilaiMasuk = "+accounting.unformat($("#nilaiMasuk").val(), ","));
    var nilaimasuk = accounting.unformat($("#nilaiMasuk").val(), ",");
    if (nilaimasuk > 0){
        nilai = $('#nilaiMasuk').val();
    } else {
        nilai = $('#nilaiKeluar').val();
    }
    
    $('#pengeluaran').val(nilai);
}

