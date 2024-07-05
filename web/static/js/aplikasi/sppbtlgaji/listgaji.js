$(document).ready(function() {

});


function grid() {

    $("#banktable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#banktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/btlgaji/json/getlistgaji",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "macam", value: $('#kodesimpeg', window.parent.document).val() == 1 ? 'GAJI' : $('#kodesimpeg', window.parent.document).val() == 2 ? 'TKD' : $('#kodesimpeg', window.parent.document).val() == 3 ? 'TRANSPORT' : 'PPH'},
                {name: "idskpd", value: $('#idskpd', window.parent.document).val()}
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

                var textid = "<input type='hidden' id='idlist" + index + "' value='" + aData['idList'] + "' />";
                var textbulan = "<input type='hidden' id='bulan" + index + "' value='" + aData['bulanRekap'] + "' />";
                var texttglpublish = "<input type='hidden' id='tglpublish" + index + "' value='" + aData['tglPublish'] + "' />";
                var textket = "<input type='hidden' id='keterangan" + index + "' value='" + aData['jenisGaji'] + " / " + aData['macamGaji'] + "' />";
                var textmacam = "<input type='hidden' id='macam" + index + "' value='" + aData['macamGaji'] + "' />";
                var textjenis = "<input type='hidden' id='jenis" + index + "' value='" + aData['jenisGaji'] + "' />";
                var texturaian = "<textarea id='uraian" + index + "'readonly style='border:none;margin:0;width:700px;'>" + aData['uraianGaji'] + "</textarea>";
                var jumkothidden = "<input type='hidden' id='nilaijumkot" + index + "' value='" + aData['jumKotor'] + "' />";
                var jumkot = accounting.formatNumber(aData['jumKotor'], 2, '.', ",");

                var kodesimpeg = aData['macamGaji'] == 'GAJI' ? 1 :
                        aData['macamGaji'] == 'TKD' || aData['macamGaji'] == 'TKD GURU' ? 2 :
                        aData['macamGaji'] == 'TRANSPORT' ? 3 : 4;

                var textkodesimpeg = "<input type='hidden' id='kodesimpeg" + index + "' value='" + kodesimpeg + "' />";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(jumkot);
                $('td:eq(6)', nRow).html(texturaian);
                $('td:eq(7)', nRow).html(textid + textbulan + textmacam + textjenis + textket + texttglpublish + textkodesimpeg + jumkothidden + "<span class='icon-ok-sign linkpilihan' onclick='simpan(" + index + ")'></span>");

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "bulanRekap", "bSortable": false, sClass: "center"},
                {"mDataProp": "bulanRekap", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglPublish", "bSortable": false, sClass: "center"},
                {"mDataProp": "macamGaji", "bSortable": false, sClass: "center"},
                {"mDataProp": "jenisGaji", "bSortable": false, sClass: "center"},
                {"mDataProp": "jumKotor", "bSortable": false, sClass: "right"},
                {"mDataProp": "uraianGaji", "bSortable": false, sClass: "center"},
                {"mDataProp": "bulanRekap", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function simpan(id) {

    $('#uraian', window.parent.document).val($("#uraian" + id).val()).change();
    $('#textjumkotsimpeg', window.parent.document).val(accounting.formatNumber($("#nilaijumkot" + id).val(), 2, '.', ",")).change();
    $('#jumkotsimpeg', window.parent.document).val($("#nilaijumkot" + id).val()).change();

    var varurl = getbasepath() + "/btlgaji/json/prosessimpanlistpegrekap";
    var dataac = [];
    //var nilailist = [];
    //var i;
    /*
     for (i = 0; i < totalindex; i++) { // list
     var id = i + 1;
     
     var pararr = {
     idbas: $("#idBas" + id).val(),
     penanda: $("#penanda" + id).val(),
     nilairinci: $("#nilaikontrak" + id).val(),
     nilaiumk: $("#nilaiumk" + id).val()
     };
     nilailist[i] = pararr;
     }
     */
    var bulanrekap = $('#bulan' + id).val();
    var tahunbulan = bulanrekap.substr(3, 4) + bulanrekap.substr(0, 2);
    var datajour = {
        //tahun: $('#tahun').val(),
        idskpd: $('#idskpd', window.parent.document).val(),
        bulan: tahunbulan,
        tglpublish: $('#tglpublish' + id).val(),
        macam: $('#macam' + id).val(),
        jenis: $('#jenis' + id).val(),
        //idkegiatan: $('#idKegiatan').val(),
        //idkontrak: $("#idKontrak").val(),
        //nilailist: nilailist

    }
    dataac = datajour;
    console.log(dataac);

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

        //bootbox.alert("Data Kontrak Rinci Berhasil Disimpan == " +data);
        $('#idlist', window.parent.document).val(data).change();



        parent.$.fancybox.close();
    });


}