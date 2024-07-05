$(document).ready(function() { 
    griddokttd();
    
}); 
function griddokttd() {
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
            "sAjaxSource":  getbasepath()+"/dokttd/json/getlistdokttd",
            "aaSorting": [[1, "asc"]],
                        "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "dokttd", value: $('#namapegawai').val()}
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
                var isceked = aData['isAktif'] == '1'?'checked':'';
                var cekaktif = "<input type='checkbox' name='isaktif"+index+"' id='isaktif"+index+"' disabled  "+isceked+" />"
                var pilih="<a class='icon-edit' href='"+getbasepath()+"/dokttd/updatedokttd/"+aData['id']+"'  ></a> - <a class='icon-remove' href='"+getbasepath()+"/dokttd/deldokttd/"+aData['id']+"' ></a>"
                $('td:eq(0)', nRow).html(index);
                $('td:eq(8)', nRow).html(cekaktif );
                $('td:eq(9)', nRow).html(pilih );
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeDok", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idNip", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "idNrk", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "namaPegawai", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "jabatan", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiMin", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiMax", "bSortable": false, sClass: "center"},
                {"mDataProp": "isAktif", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}
            ]
        });
      $("div.top").html("<a  href='"+getbasepath()+"/dokttd/adddokttd' class='btn blue' style='float: right'>Tambah Data</a>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}
