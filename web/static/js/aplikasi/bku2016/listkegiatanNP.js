$(document).ready(function() {
   
});

var ketval; 

function grid() {
    
    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath()+"/bku/json/listkegiatanNPDjson",
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "kode", value: $('#kode').val()},
                        {name: "nama", value: $('#nama').val()},
                        {name: "tahun", value: $('#tahun').val()},
                        {name: "keterangan", value: "NP"},
                        {name: "idskpd", value: $('#idskpd').val()}
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
                var textnama = "<input type='hidden' id='nama" + aData['idKegiatan'] + "' value='" + aData['namaKeg']+"' />";
                var textkode = "<input type='hidden' id='kode" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"' />";
                var textket = "<input type='hidden' id='ket" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"/"+aData['namaKeg'] + "' />";
                var nilaianggaran = accounting.formatNumber(aData['nilaiAnggaran'], 2, '.', ",");
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(nilaianggaran);
                $('td:eq(4)', nRow).html(textket+textkode+textnama+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idKegiatan'] + ")'></span>");
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"}, 
                {"mDataProp": "kodeKeg", "bSortable": true, sClass: "center"},
                {"mDataProp": "namaKeg", "bSortable": true, sClass: "left"},
                {"mDataProp": "nilaiAnggaran", "bSortable": true, sClass: "right"},
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    
}
