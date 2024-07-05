$(document).ready(function() {
    setBtnSimpan();
    $('#btncetakxls').attr("disabled", true);
});

var banyak = 0;

function setBtnSimpan(){
    if ($("#idskpdpop").val() == ""){
        $('#btnsimpan').attr("disabled", true);
    } else {
       // $('#btnsimpan').attr("disabled", false);
    }
}


function simpan() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var tglawal = $("#tglAwal").val();
    var tglakhir = $("#tglAkhir").val();

    var d, m, y, dd, mm, yy, tglawalgab, tglakhirgab;

    if (tglawal !== "" || tglakhir !== "") {

        y = tglawal.substring(6);
        m = tglawal.substr(3, 2);
        d = tglawal.substr(0, 2);
        tglawalgab = y + m + d;

        yy = tglakhir.substring(6);
        mm = tglakhir.substr(3, 2);
        dd = tglakhir.substr(0, 2);
        tglakhirgab = yy + mm + dd;

       
        var varurl = getbasepath() + "/prosesBku/json/prosessimpan";
        var dataac = [];

        var datajour = {
            tahun: tahun,
            idskpd: idskpd,
            tglawal : tglawalgab,
            tglakhir : tglakhirgab
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
            bootbox.alert("Proses Jurnal BKU Berhasil Disimpan");
        }); 
    } else {
        bootbox.alert("Pengisian Data Harus Lengkap");
    }

}

function gridbku() {
    
    var tanggal1 = $('#tglAwal').val();
    var tanggal2 = $('#tglAkhir').val();
    
    if (tanggal1 !== "" && tanggal2 !== "") {
        
        if (typeof myTable == 'undefined') {
            myTable = $('#jourskpdtable').dataTable({
                "bPaginate": true,
                "sPaginationType": "bootstrap",
                "bJQueryUI": false,
                "bProcessing": true,
                "bServerSide": true,
                "bInfo": true,
                "bScrollCollapse": true,
                //"sScrollY": ($(window).height() * 108 / 100),
                "bFilter": false,
                "sAjaxSource": getbasepath() + "/prosesBku/json/listjournalbku",
                "aaSorting": [[1, "asc"]],
                "fnDrawCallback": function() {
                    //formatnumberonkeyup();
                    //$(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
                },
                "fnServerParams": function(aoData) {
                    aoData.push(
                            {name: "tahun", value: $('#tahun').val()},
                    {name: "idskpd", value: $('#idskpdpop').val()},
                    {name: "tgl1", value: $('#tglAwal').val()},
                    {name: "tgl2", value: $('#tglAkhir').val()}
                    );
                },
                "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                    //bootbox.alert("Panjang == "+ aaData.length);

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
                    
                    var nilaimasuk = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                    var nilaikeluar = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                    var saldo = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");
                    
                    if(index > 0){
                        $('#btncetakxls').attr("disabled", false);
                        $('#btnsimpan').attr("disabled", false);
                    }
                    
                    $('td:eq(0)', nRow).html(index);
                    $('td:eq(13)', nRow).html(nilaimasuk);
                    $('td:eq(14)', nRow).html(nilaikeluar);
                    //$('td:eq(15)', nRow).html(saldo);

                    return nRow;

                },
                "aoColumns": [
                    {"mDataProp": "noUrut", "sWidth": "5%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "tglPosting", "sWidth": "10%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "kodeTransaksi", "sWidth": "10%", "bSortable": false, sClass: "left"},
                    {"mDataProp": "jenis", "sWidth": "30%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "beban", "sWidth": "15%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "noBukti", "sWidth": "15%", "bSortable": false, sClass: "left"},
                    {"mDataProp": "tglBukti", "sWidth": "10%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "kodeKeg", "sWidth": "15%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "namaKeg", "sWidth": "5%", "bSortable": false, sClass: "left"},
                    {"mDataProp": "kodeAkun", "sWidth": "10%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "namaAkun", "sWidth": "30%", "bSortable": false, sClass: "left"},
                    {"mDataProp": "keterangan", "sWidth": "10%", "bSortable": false, sClass: "left"},
                    {"mDataProp": "kodeUangPersediaan", "sWidth": "15%", "bSortable": false, sClass: "center"},
                    {"mDataProp": "nilaiMasuk", "sWidth": "15%", "bSortable": false, sClass: "kanan"},
                    {"mDataProp": "nilaiKeluar", "sWidth": "15%", "bSortable": false, sClass: "kanan"}
                   // {"mDataProp": "saldoKas", "sWidth": "15%", "bSortable": false, sClass: "kanan"}


                ]
            });

        }
        else
        {
            myTable.fnClearTable(0);
            myTable.fnDraw();
        }
    }

}

function cetakjurnalxls() {
    window.location.href = getbasepath() + "/prosesBku/xls/bkuxls?tahun=" + $("#tahun").val()
            + "&idskpd=" + $('#idskpdpop').val() + "&tglAwal=" + $('#tglAwal').val() + "&tglAkhir=" + $('#tglAkhir').val();
}