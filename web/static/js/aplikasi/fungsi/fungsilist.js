$(document).ready(function() {
    grid();
});


function grid( ) {
    $("#fungsitable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#fungsitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource":  getbasepath()+"/common/json/listfungsijson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "fungsi", value: $('#namafungsi').val()}
                );
            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData, 
                    "fnDrawCallback": function () {
                formatnumberonkeyup();
                //$(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                //  gettotalspddanspp( );
                getbanyakspdrinci();
            },
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var isceked = aData['isAktif'] == '1'?'checked':'';
                var cekaktif = "<input type='checkbox' name='isaktif"+index+"' id='isaktif"+index+"' disabled  "+isceked+" />"
                var pilih="<a class='icon-edit' href='"+getbasepath()+"/common/editfungsi/"+aData['idFungsi']+"'  ></a> - <a class='icon-remove' href='"+getbasepath()+"/common/delfungsi/"+aData['idFungsi']+"' ></a>"
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(cekaktif );
                $('td:eq(4)', nRow).html(pilih );
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idFungsi", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeFungsi", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaFungsi", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "isAktif", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "idFungsi", "bSortable": false, sClass: "center"}
            ]
        });
      $("div.top").html("<a  href='"+getbasepath()+"/common/addfungsi' class='btn blue' style='float: right'>Tambah Data</a>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}
