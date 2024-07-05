$(document).ready(function() {
    $("#tglAwal").datepicker("setDate", new Date());
    $("#tglAkhir").datepicker("setDate", new Date());
    setUser();
    
});

function grid() {
    var tglAwal = $('#tglAwal').val();
    var tglAkhir = $('#tglAkhir').val();
    /*
    if ((tglAwal !== "") && (tglAkhir !== "")) {
        
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
                    {name: "idskpd", value: $('#idskpd').val()},
                    {name: "tglawal", value: $('#tglAwal').val()},
                    {name: "tglakhir", value: $('#tglAkhir').val()}
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
                    var nilaikeluar = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");
                    var saldo = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");
                    
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
                    //{"mDataProp": "saldoKas", "sWidth": "15%", "bSortable": false, sClass: "kanan"}


                ]
            });

        }
        else
        {
            myTable.fnClearTable(0);
            myTable.fnDraw();
        }
    }
*/
}

function setUser() {
    //var idskpd = $('#idskpd').val();
    
    $.getJSON(getbasepath() + "/transaksiBankDki/json/getComboUser", {},
    function(result) {
        //console.log(result);
        
        var banyak, kode,ket;
        var opt = "";
        
        banyak = result.aData.length;
        
        if (banyak > 0){
             for (var i = 0; i < banyak; i++) {
                 kode = result.aData[i]['idUser'];
                 ket = result.aData[i]['namaUser'];
                
                 opt += '<option value="'+ kode + '">' + ket + '</option>';
            }
        }
       
        $("#user").html(opt);
        
    });
    
}
