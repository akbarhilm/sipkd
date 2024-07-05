$(document).ready(function() {
    gridspj();
    var banyakspjbelumsah = $('#banyakspjbelumsah').val().toString();
    if(banyakspjbelumsah == "1"){
         $('#tambahspj').hide();
    }

});
function gridspj() {
    var urljson = getbasepath() + "/spj/json/getlistspj";
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
                //  {name: "namaskpd", value: $('#skpd').val()},
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
                var isceked = aData['nihil'] == '1' ? 'checked' : '';
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                var edit = "<a href='" + getbasepath() + "/spj/editspjbl/" + aData['idSpj'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/spj/deletespjbl/" + aData['idSpj'] + "' class='icon-remove' ></a>";
                var nilaispjbl = accounting.formatNumber(aData['nilaiSpjBl'], 2, '.', ",");
                var nilaispjbtl = accounting.formatNumber(aData['nilaiSpjBtl'], 2, '.', ",");
                $('td:eq(0)', nRow).html(index);
                //$('td:eq(3)', nRow).html(nilaispjbtl);
                $('td:eq(3)', nRow).html(nilaispjbl);
                //$('td:eq(5)', nRow).html(cekaktif);
                $('td:eq(5)', nRow).html(edit);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpj", "bSortable": true, sClass: "center"},
                {"mDataProp": "noSpj", "bSortable": true, sClass: "center"},
                {"mDataProp": "bulan", "bSortable": true, sClass: "center"},
                {"mDataProp": "v_spjbl", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "nihil", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpj", "bSortable": false, sClass: "center"}
           
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function pindahhalaman() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spj/addspjbl/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spj/addspjbl");
    }

}

