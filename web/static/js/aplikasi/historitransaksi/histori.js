$(document).ready(function () {
    // tampil();

    //getRekKor();
});

//function ws(){
//
//
//      $.getJSON("http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD",{nrk:$("#nrk").val()},
//                function(data) {
//                   $("#nama").val(data.results[0]['NAMA']);
//                   $("#nip").val(data.results[0]['NIP18'])
//                }
//         );
//
//}

function getsaldo() {

    var url = getbasepath() + "/bkustrans/json/saldoakhir"
    var rekbos = $("#rekeningBOS").val();
    var rekbop = $("#rekeningBOP").val();
    var tipe = $("#tiperek").val();

    $.getJSON(url, {tipe: tipe, rekeningbos: rekbos, rekeningbop: rekbop},
            function (data) {
                //console.log(data.aData[0])
                $("#saldoakhir").val(accounting.formatNumber(data.aData[0]['V_TRX_SALDOAKHIR'], 2, '.', ","));
                // $("#saldokeluar").val(accounting.formatNumber(data.aData[0]['V_TRX_SALDOAKHIR'], 2, '.', ","));
            }
    );

}

function tampil() {
    getsaldo();
    var rekbos = $("#rekeningBOS").val();
    var rekbop = $("#rekeningBOP").val();
    var tipe = $("#tiperek").val();
    $("#usertable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#usertable').dataTable({
            "bPaginate": false,
            "sPaginationType": "bootstrap",
            "bJQueryUI": true,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": false,
            "bDestroy": true,
            //"aLengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
            //"iDisplayLength": 10,
            //"sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkustrans/json/listtrans",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "rekeningbos", value: rekbos},
                        {name: "rekeningbop", value: rekbop},
                        {name: "tipe", value: tipe}
                );
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnFooterCallback": function (nRow, aaData, iStart, iEnd, iDisplay) { //nampilin table footer total
                var totaltr = 0;
                var totalkl = 0;

                for (var i = iStart; i < iEnd; i++) {
                    totaltr += aaData[i]['terima'];
                    ;
                    totalkl += aaData[i]['keluar'];
                }
                $("#sumterima").val(accounting.formatNumber(totaltr, 2, '.', ","));
                $("#sumkeluar").val(accounting.formatNumber(totalkl, 2, '.', ","));

            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var jam = aData['jamTrx'];
                var h = jam.substring(0, 2);
                var m = jam.substring(2, 4);
                var s = jam.substring(4);
                var terima = accounting.formatNumber(aData['terima'], 2, '.', ",");
                var keluar = accounting.formatNumber(aData['keluar'], 2, '.', ",")
                // var isceked = aData['kodeAktif'] == '1' ? 'checked' : '';
                // var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                //console.log(aData['tglTrx']);

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(h + ":" + m + ":" + s);
                $('td:eq(4)', nRow).html(terima);
                $('td:eq(5)', nRow).html(keluar);
                //$('td:eq(4)', nRow).html(pilih);


                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "tglTrx", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglTrx", "bSortable": false, sClass: "center"},
                {"mDataProp": "jamTrx", "bSortable": false, sClass: "center"},
                {"mDataProp": "keterangan", "bSortable": true, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "terima", "bSortable": true, sClass: "right"},
                {"mDataProp": "keluar", "bSortable": false, sClass: "right"},
                {"mDataProp": "generateTime", "bSortable": false, sClass: "center"},
            ]
        });
        // $("div.top").html("<a class='fancybox fancybox.iframe btn blue' href='" + getbasepath() + "/sekolahpopup/listsekolahpa?target=_top'  style='float: right'>Tambah Data</a>");

    } else
    {
        myTable.fnDestroy();
        myTable.fnDraw();
    }
}

function getRekKor() {
    $("#tmp").attr('disabled', true);
    var rek;
    var rekbos = $("#rekeningBOS").val();
    var rekbop = $("#rekeningBOP").val();
    var tipe = $("#tiperek").val();
    if (tipe == 'BOS') {
        rek = rekbos;
    }
    if (tipe == 'BOP') {
        rek = rekbop;
    }

    var varurl = getbasepath() + "/bkustrans/json/newrekeningkoran";
    var dataac = [];
    var datajour = {
        account: rek

    };
    dataac = datajour;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function (data) {
            //console.log(data);
            tampil();
            $("#tmp").attr('disabled', false);
        },
        error: function (xhr) {
            console.error(xhr);
            bootbox.alert("Sambungan Bank DKI Terputus");
            $("#tmp").attr('disabled', false);
            //$('#btnSimpan').attr("disabled", true);
        }
    }).always(function (data) {

    });

}

function getRekKor2() {
    $("#tmp").attr('disabled', true);
    var rek;
    var rekbos = $("#rekeningBOS").val();
    var rekbop = $("#rekeningBOP").val();
    var tipe = $("#tiperek").val();
    if (tipe == 'BOS') {
        rek = rekbos;
    }
    if (tipe == 'BOP') {
        rek = rekbop;
    }

    var varurl = getbasepath() + "/bkustrans/json/rekeningkoran";
    var dataac = [];
    var datajour = {
        account: rek

    };
    dataac = datajour;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function (data) {
            //console.log(data);
            tampil();
            $("#tmp").attr('disabled', false);
        },
        error: function (xhr) {
            console.error(xhr);
            bootbox.alert("Sambungan Bank DKI Terputus");
            $("#tmp").attr('disabled', false);
            //$('#btnSimpan').attr("disabled", true);
        }
    }).always(function (data) {

    });

}

function cetak() {
    var aaa = $("#idSekolah").val();
    var rekbop = $("#rekeningBOP").val();
    var rekbos = $("#rekeningBOS").val();
    var rek;
    //var eee = aaa + "-" + "ADM-PENGGUNA.pdf";
    var tipe = $("#tiperek").val();
    if (tipe == 'BOP') {
        rek = rekbop;
    } else {
        rek = rekbos;
    }
    window.location.href = getbasepath() + "/bkustrans/json/prosescetak?idsekolah=" + aaa + "&tipe=" + tipe + "&rekening=" + rek;


}
