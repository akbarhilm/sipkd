$(document).ready(function() {
    grid();
});


function grid( ) {
    $("#lokettable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#lokettable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "sDom": '<"top">irt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource":  getbasepath()+"/trpnrmloket/json/listdatajson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                  {name: "namaLoket", value: $('#namaloket').val()}
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
                var cekaktif = "<input type='checkbox' name='isAktif"+index+"' id='isAktif"+index+"' disabled  "+isceked+" />"
                var pilih="<a class='icon-edit' href='"+getbasepath()+"/trpnrmloket/editdata/"+aData['idLoket']+"'  ></a>"
                
                //<a class='icon-remove' href='"+getbasepath()+"/trpnrmloket/deldata/"+aData['idLoket']+"' ></a>"
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(cekaktif );
                $('td:eq(5)', nRow).html(pilih );
                
          return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idLoket", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeLoket", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaLoket", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "alamatLoket", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "isAktif", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "idLoket", "bSortable": false, sClass: "center"}
            ]
        });
        $("div.tambah-data").html("<a  href='"+getbasepath()+"/trpnrmloket/tambahdata' class='btn blue' style='float: right'> Tambah Data </a>");
        
    }
    
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}