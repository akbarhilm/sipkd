
 $(document).ready(function() {
    
    if ($('#beban').val() == "TU"){
        document.getElementById("labelkegiatan").style.display = "block"; 
        $("#textbeban").val("TU");
        setNilaiTU();
    } else{
        $("#textbeban").val("UP/GU");
        document.getElementById("labelkegiatan").style.display = "none"; 
    }
 });


function setNilaiTU() {
    $.getJSON(getbasepath() + "/setor/json/getSisaTU", {idskpd:$("#idskpd").val(), idkegiatan:$("#idKegiatan").val()},
    function(result) {
        var nilai;
        
        nilai = result.aData;
        //$("#nilaiSetor").val(nilai);
        cekNilai(nilai);
    });
}

function cekNilai(nilai) {
    //console.log("nilai == "+nilai);
    
    var sisaup =  accounting.unformat($("#sisaup").val(), ",");
    var nilaisetor = accounting.unformat(nilai, ",");
    console.log("nilaisetor = "+nilaisetor);
    
     if (sisaup < nilaisetor){
         bootbox.alert("Nilai Setoran Tidak Boleh Lebih Besar dari Sisa Kas UP");
        $("#nilaiSetor").val(sisaup);
    }
    
}