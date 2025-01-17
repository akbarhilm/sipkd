$(document).ready(function() {
    getlistspdbl($("#idskpd").val());
});
function getlistspdbl(id) {
    if (id != null && id != 'undefined'  &&  $.trim(id).length > 0 ){
        $.getJSON(getbasepath() + "/spd/pengajuanbl/json/getpagudansisabl/" + id,
                function(result) {
                    $('#pagubtl').val(accounting.formatNumber(result.PAGU));
                    $('#sisaspd').val(accounting.formatNumber(result.VSPD));
                });

        gridspdblmasterlist();
    }

}
function pindahhalamanadd() {
    if ($("#idskpd").val().trim()) {
        window.location.replace(getbasepath() + "/spd/pengajuanbl/bl/addskpd/" + $("#idskpd").val().trim());
    } else {
        window.location.replace(getbasepath() + "/spd/pengajuanbl/bl/add");
    }
}

function gridspdblmasterlist() {
    var urljson = getbasepath() + "/spd/pengajuanbl/bl/json/getlistspd";
    var baseurl = getbasepath();
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
            "aaSorting": [[0, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "id", value: $('#idskpd').val()}
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
                var textnamaskpd = "<input type='hidden' id='namaSkpd" + aData['idSkpd'] + "' value='" + aData['namaSkpd'] + "' />"
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(getTanggal(aData['tglSpd']));
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['nilaiSpd']));
                var urledit = baseurl + "/spd/pengajuanbl/bl/edit";
                var urldel = "#"
                var objekedit = "<a   title='Ubah Data'  class='icon-edit linkpilihan' href=" + urledit + "/" + aData['idSpd'] + " '></a>";
                // console.log(urledit+" == "+urlhapusspd);
                var objekdel = "<a  title='Hapus Data' onclick=hapusspddanrincibyidspd(" + aData['noSpd'] + "," + aData['idSpd'] + ",'" + baseurl + "')   class='icon-remove linkpilihan' href=" + urldel + "/" + aData['idSpd'] + "></a>";
                var editadd = textnamaskpd + objekedit + " - " + objekdel;
                $('td:eq(5)', nRow).html(aData['status'] == 'PENGAJUAN' ? editadd : '-');
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpd", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglSpd", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "nilaiSpd", "bSortable": true, sClass: "kanan", sDefaultContent: "-"},
                {"mDataProp": "status", "bSortable": false},
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function hapusspddanrincibyidspd(nospd, idspd, baseurl) {
    var urlhapusspd = baseurl + "/spd/pengajuanbl/json/hapusspddanrincibyspd";
    bootbox.confirm("Anda akan menghapus data SPD dengan nomor " + nospd + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspd": idspd, "nospd": nospd, "skpd": $("#skpd").val()})
            }).always(function(data) {
                gridspdblmasterlist(baseurl);
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}