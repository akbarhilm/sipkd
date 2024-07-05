$(document).ready(function() {
    tanggalAwal();
    document.getElementById("akunpilih").style.visibility = "hidden";
    $('#btncetak').attr("disabled", true);
    
    $("#idskpdpop").val("");
    $("#skpdpop").val("");
    
    $("#idbaspop").val("");
    $("#idakunpop").val("");
    $("#akunketpop").val("");
    
});

// global variable
var idskpd, akun;
var baris = 0;

function tanggalAwal(){
    var tahun = $("#tahun").val();
    var tglgabung = "01/01/"+tahun;
    
    $("#tglPostingAwal").val(tglgabung);
    
}

function ceklengkap(){
    document.getElementById("akunpilih").style.visibility = "visible";
    idskpd = $("#idskpdpop").val();
    akun = $("#idbaspop").val();
    if ((idskpd !== "") && (akun !== "")){
        settglposting();
    } 
}

function settglposting(akun){
    
    idskpd = $("#idskpdpop").val();
   
    if (akun !== ""){
        $.getJSON(getbasepath() + "/bukubesarskpdall/json/gettanggalposting", {idskpd: idskpd, akun: akun},
        function(result) {
            var hasil = result.aData;
            if (hasil !== null){
                $("#tglPosting").val(hasil); 
            }
        });
    }
}

function setbtncetak(){
    $('#btncetak').attr("disabled", false);
    //gridbukubesar();
}

function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var kodeakun = $("#idakunpop").val();

    var eee = tahun+"-"+"JURNAL-BUKU BESAR-SKPD"+"-"+idskpd+"-"+kodeakun+".pdf";
    
    if (idskpd == ""){
        bootbox.alert("SKPD Harus Diisi");
    }else if (kodeakun == ""){
        bootbox.alert("Akun Harus Diisi");
    }else{
        //bootbox.alert("idskpd == "+ idskpd);
        window.location.href= getbasepath() + "/bukubesarskpdall/json/prosescetak?tahun="+tahun+"&idskpd="+idskpd+"&namafile="+eee+"&kodeakun="+kodeakun;
    }
}

function formattanggal(tgl){
    var yy = tgl.substring(6);
    var mm = tgl.substr(3, 2);
    var dd = tgl.substr(0, 2);
    
    var tglgabung = yy+mm+dd;
    
    return tglgabung;
}

function simpan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var kodeakun = $("#idakunpop").val();
    var postawal = formattanggal($("#tglPostingAwal").val());
    var postakhir = formattanggal($("#tglPosting").val());
        
    if (idskpd == ""){
        bootbox.alert("Data SKPD Tidak Boleh Kosong");
    }else if (kodeakun == ""){
        bootbox.alert("Data Akun Tidak Boleh Kosong");
    }else if (postakhir == ""){
        bootbox.alert("Tanggal Posting Tidak Boleh Kosong");
    }else{

        var varurl = getbasepath() + "/bukubesarskpdall/json/prosessimpan";
        var dataac = [];

        var datajour = {
            tahun : tahun,
            idskpd : idskpd,
            kodeakun: kodeakun,
            postawal: postawal,
            postakhir: postakhir
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
            bootbox.alert("Jurnal Buku Besar SKPD Berhasil Disimpan");
            $('#btnproses').attr("disabled", true);
            gridbukubesar(); 
        });
    }
      
}

function setbtnproses(){
    $('#btnproses').attr("disabled", false);
}

function gridbukubesar() {
    var akunn = $('#idbaspop').val();
    console.log("akun = "+ akunn);
    
    var urljson = getbasepath() + "/bukubesarskpd/json/getlistbukubesar";
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                    {name: "idskpd", value: $('#idskpdpop').val()},
                    {name: "kodeakun", value: $('#idakunpop').val()},
                    {name: "tahun", value: $('#tahun').val()}
                );
            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var nilaidebet = accounting.formatNumber(aData['debet'], 2, '.', ",");
                var nilaikredit = accounting.formatNumber(aData['kredit'], 2, '.', ",");
                var nilaisaldo = accounting.formatNumber(aData['saldo'], 2, '.', ",");
                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaidebet);
                $('td:eq(6)', nRow).html(nilaikredit);
                $('td:eq(7)', nRow).html(nilaisaldo);
                
                baris = baris + index;
                
                if (baris > 0){
                    $('#btnproses').attr("disabled", true);
                    $('#btncetak').attr("disabled", false);
                } else if (baris <= 0){
                    $('#btncetak').attr("disabled", true);
                }
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "nourut", "bSortable": true, sClass: "center"},
                {"mDataProp": "nourut", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglPosting", "bSortable": true, sClass: "center"},
                {"mDataProp": "nojurnal", "bSortable": false, sClass: "left"},
                {"mDataProp": "nodokumen", "bSortable": false, sClass: "left"},
                {"mDataProp": "debet", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "kredit", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "saldo", "bSortable": false, sClass: "kanan"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function cek(){
    
}
