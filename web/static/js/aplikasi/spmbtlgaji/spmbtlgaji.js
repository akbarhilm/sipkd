$(document).ready(function() {
    gridsppup();

});
function gridsppup() {
    var urljson = getbasepath() + "/spmbtlgaji/json/getlistspmbtlgaji";
    // var urljson = getbasepath() + "/sppup/json/getlistspdbl";
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
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
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
                var batas = aData['batasWaktu'];
                var d = new Date();
                var tgl = d.getUTCDate();
                var bln = d.getMonth() + 1;

                console.log("batas = " + batas);
                console.log("tgl = " + tgl);
                console.log("bln = " + bln);
               
                var potongan = " - ";
                if (tgl > batas && bln == 12) { // tambahkan validasi bulan
                    potongan = " - ";
                } else {
                    if (aData['potongan'] == true) {
                        potongan = "<a href='" + getbasepath() + "/spmpotayat/indexspmgaji/" + aData['idspm'] + "/" + aData['id'] + "/" + aData['kodeSimpeg'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
                    }
                }

                var edit = "<a href='" + getbasepath() + "/spmbtlgaji/editspmbtlgaji/" + aData['id'] + "'  title='Klik disini untuk melakukan entry/update SPM' class='icon-edit' ></a>";


                var batal = "";
                if (aData['idspm']) {
                    batal = "<a title='Klik disini untuk membatalkan spm' class='icon-remove linkpilihan' href='#' onclick=hapusspm(" + aData['idspm'] + ",'" + aData['noSpm'] + "') ></a>";

                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                $('td:eq(4)', nRow).html(edit + "  " + batal);
                $('td:eq(5)', nRow).html(potongan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "noSpp", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
/*
function pindahhalamanadd() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spmbtlgaji/addspppup/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spmbtlgaji/addspppup");
    }

}
*/
function hapusspm(idspm, nospm) {
    var urlhapusspd = getbasepath() + "/spmbtlgaji/json/hapusdataspm";
    bootbox.confirm("Anda akan membatalkan  data SPM dengan nomor " + nospm + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspm": idspm, "nospm": nospm})
            }).always(function(data) {
                gridsppup();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}