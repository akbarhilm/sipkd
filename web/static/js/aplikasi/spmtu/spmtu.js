$(document).ready(function () {
    gridspptu();

});
function gridspptu() {
    var urljson = getbasepath() + "/spmtu/json/getlistspmtu";
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
            "fnDrawCallback": function () {
                gettotalspddanspp( );
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()}
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
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var edit = "<a href='" + getbasepath() + "/spmtu/editspmtu/" + aData['id'] + "'  title='Klik disini untuk melakukan entry/update SPM' class='icon-edit' ></a>";
                var batal = "";
                if(aData['idspm']){
                        batal = "<a title='Klik disini untuk membatalkan spm' class='icon-remove linkpilihan' href='#' onclick=hapusspm(" + aData['idspm'] + ",'" + aData['noSpm'] + "') ></a>";
              
                }
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                $('td:eq(4)', nRow).html(edit+"      "+batal);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "noSpp", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center", sDefaultContent: "-"},
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

function pindahhalamanadd() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spptu/addsppptu/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spptu/addsppptu");
    }

}

function gettotalspddanspp( ) {
    $.getJSON(getbasepath() + "/spptu/json/gettotalspddanspp",
            {
                tahunAnggaran: $("#tahun").val(),
                idskpd: $("#idskpd").val(),
                idspp: $("#id").val(),
                kodekegiatan: $("#kodekegiatanfilter").val(),
                spdno: $("#nospdfilter").val()
            },
    function (result) {
        $('#totalspd').val(accounting.formatNumber(result.V_SPD, 2, '.', ","));
        $('#totalspp').val(accounting.formatNumber(result.V_SPP, 2, '.', ","));
    });

}


function hapusspm(idspm, nospm) {
    var urlhapusspd = getbasepath() + "/spmup/json/hapusdataspm";
    bootbox.confirm("Anda akan membatalkan  data SPM dengan nomor " + nospm + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function (result) {
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
            }).always(function (data) {
                gridspptu();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}