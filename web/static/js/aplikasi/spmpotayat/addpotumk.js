$(document).ready(function() {

});

var sppval, spmval, kontrakval, jumlahakun, kodeumk;


function grid() {
    jumlahakun = 0;
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 100,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/spmpotuangmuka/json/listpotumkjson",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: sppval},
                {name: "idspm", value: spmval},
                {name: "idspm", value: spmval},
                {name: "idkontrak", value: kontrakval},
                {name: "kodeumk", value: kodeumk}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

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

                jumlahakun = index;

                var idbas = "<input type='hidden' id='idbas" + index + "' value='" + aData['idBas'] + "' />";
                var namaakun = "<textarea id='akunnama" + index + "'readonly style='border:none;margin:0;width:300px;'>" + aData['namaAkun'] + "</textarea>";

                var nilaipot = "<input type='text' name='nilaipot" + index + "' id='nilaipot" + index + "' value='" + aData['nilaiPotongan'] + "' onkeyup='pasangvalidasi(" + index + ")' class='inputmoney'>";

                var paguumk = "<input type='text' name='paguumk" + index + "' id='paguumk" + index + "'  class='inputmoney'  value='" + aData['paguUmk'] + "' readOnly='true' />";
                var nilaiumk = "<input type='text' name='nilaiumk" + index + "' id='nilaiumk" + index + "'  class='inputmoney'  value='" + aData['nilaiUmk'] + "' readOnly='true' />";
                var nilaisebelum = "<input type='text' name='nilaisebelum" + index + "' id='nilaisebelum" + index + "'  class='inputmoney'  value='" + aData['nilaiSebelum'] + "' readOnly='true' />";
                var nilaisisa = "<input type='text' name='nilaisisa" + index + "' id='nilaisisa" + index + "'  class='inputmoney'  value='" + aData['sisaPotongan'] + "' readOnly='true' />";

                $('td:eq(0)', nRow).html(index + idbas);
                $('td:eq(2)', nRow).html(namaakun);
                $('td:eq(3)', nRow).html(paguumk);
                $('td:eq(4)', nRow).html(nilaiumk);
                $('td:eq(5)', nRow).html(nilaisebelum);
                $('td:eq(6)', nRow).html(nilaisisa);
                $('td:eq(7)', nRow).html(nilaipot);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "kodeAkun", "bSortable": false, sClass: "center", "sWidth": "10%"},
                {"mDataProp": "namaAkun", "bSortable": true, sClass: "right", "sWidth": "14%"},
                {"mDataProp": "paguUmk", "bSortable": true, sClass: "right", "sWidth": "14%"},
                {"mDataProp": "nilaiUmk", "bSortable": false, sClass: "right", "sWidth": "14%"},
                {"mDataProp": "nilaiSebelum", "bSortable": false, sClass: "right", "sWidth": "14%"},
                {"mDataProp": "sisaPotongan", "bSortable": true, sClass: "right", "sWidth": "15%"},
                {"mDataProp": "nilaiPotongan", "bSortable": true, sClass: "right", "sWidth": "15%"}

            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function pasangvalidasi(index) {
    var nilaiinput = accounting.unformat($("#nilaipot" + index).val(), ",");
    var nilaisisa = accounting.unformat($("#nilaisisa" + index).val(), ",");
    var nilaiumk = accounting.unformat($("#nilaiumk" + index).val(), ",");
    var nilaivalidasi;

    if (nilaiumk < nilaisisa) { // ditambah 9 januari 2018; karena validasi harus baca ke nilai umk yg diajukan di kontrak rinci
        nilaivalidasi = nilaiumk;
    } else {
        nilaivalidasi = nilaisisa;
    }

    if (nilaiinput > nilaivalidasi) {
        bootbox.alert("Nilai Potongan tidak boleh lebih besar dari Nilai/Sisa UMK."); // ganti pesan : sisa bku -> sisa spd
        //$('#nilaiinput' + index).val(accounting.formatNumber(nilaisisa, 2, '.', ","));
        $('#nilaipot' + index).autoNumeric('set', nilaivalidasi); // nilaisisa harus yang di unformat :)
    }
}

function simpan() {
    //console.log("jumlahakun = "+jumlahakun);
    var varurl = getbasepath() + "/spmpotuangmuka/json/prosessimpanpotumk";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < jumlahakun; i++) { // list
        var id = i + 1;

        var pararr = {
            nilaipot: parseFloat(accounting.unformat($('#nilaipot' + id).val(), ",")),
            idbas: $("#idbas" + id).val()
        };
        nilailist[i] = pararr;
    }

    var datajour = {
        tahun: $("#tahun").val(),
        idskpd: $("#idskpd").val(),
        idspm: spmval,
        cpot: "31", // UMK
        nilailist: nilailist

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

        bootbox.alert("Data Potongan UMK Berhasil Disimpan");
        // kalo pale pop up
        //$('#ketUmkpop', window.parent.document).val("INPUT UMK").change();
        // parent.$.fancybox.close();

        window.location.href = getbasepath() + "/spmpotuangmuka/indexspm/" + spmval; // ke form potongan
    });

}

function kembali() {
    var a = document.getElementById("btnkembali");
    a.href = getbasepath() + "/spmpotuangmuka/indexspm/" + spmval;

}

function getKodeUmk() {
    var idskpd = $('#idskpd').val();
    var tahun = $('#tahun').val();
    console.log("idkontrak = " + kontrakval);

    $.getJSON(getbasepath() + "/spmpotuangmuka/json/getKodeUmk", {idskpd: idskpd, tahun: tahun, idkontrak: kontrakval},
    function(result) {
        kodeumk = result.aData[0]['kodeUmk'];
        grid();
    });
}
