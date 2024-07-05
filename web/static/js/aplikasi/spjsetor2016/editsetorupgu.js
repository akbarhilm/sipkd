
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