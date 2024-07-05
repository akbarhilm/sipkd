$(document).ready(function() {
    //gridspj();
    //bootbox.alert("CEK NILAI TAHUN = "+$('#tahun').val());

    $('#buttoninduk').attr("disabled", true);
});
function setbtnproses(banyak) {
    var bulan = $('#idSpj').val();

    if (banyak < 1) {
        $('#buttoninduk').attr("disabled", true);
    } else {
        $('#buttoninduk').attr("disabled", false);
    }
}

function gridspj() {
    var urljson = getbasepath() + "/journalspj/json/getlistjournalspj";
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
                        // {name: "idskpd", value: $('#idskpd').val()},
                                {name: "tahun", value: $('#tahun').val()},
                        {name: "bulan", value: $('#bulan').val()},
                        {name: "idSpj", value: $('#idSpj').val()}

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
                var isceked = aData['nihil'] == '1' ? 'checked' : '';
                var nilaispj = accounting.formatNumber(aData['nilai_spj'], 2, '.', ",");
                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(nilaispj);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpj", "bSortable": true, sClass: "center"},
                {"mDataProp": "kodekeg", "bSortable": true, sClass: "center"},
                {"mDataProp": "namakeg", "bSortable": true, sClass: "left"},
                {"mDataProp": "akunbelanja", "bSortable": true, sClass: "left"},
                //{"mDataProp": "akunjurnal", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilai_spj", "bSortable": false, sClass: "kanan"}

            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function simpan() {

    var varurl = getbasepath() + "/journalspj/json/prosessimpan";
    var dataac = [];

    var datajour = {
        bulan: $("#bulan").val(),
        idspj: $("#idSpj").val(),
        idskpd: $("#idskpdpop").val(),
        tahun: $("#tahun").val()
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
        bootbox.alert("Proses Jurnal SPJ Berhasil Disimpan");
    });

}

function pindahhalaman() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spj/addspjbl/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spj/addspjbl");
    }

}

function setBulan(idskpd) {
    var tahun = $('#tahun').val();

    $.getJSON(getbasepath() + "/journalspj/json/setBulan", {idskpd: idskpd, tahun: tahun},
    function(result) {
        //console.log(result);

        var banyak, kode, ket, idspj;
        var opt = "";

        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                idspj = result.aData[i]['idSpj'];
                kode = result.aData[i]['kodeBulan'];
                ket = result.aData[i]['namaBulan'];

                opt += '<option value="' + kode + '" selected>' + ket + '</option>';
            }
        }
        $('#idSpj').val(idspj);
        $("#bulan").html(opt);
        gridspj();
        setbtnproses(banyak);

    });

}

function setIdskpd(idpop) {
    $('#idskpd').val(idpop);
}