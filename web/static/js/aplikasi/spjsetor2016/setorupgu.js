
$(document).ready(function() {
    getBebanSetorUP();
    grid();
    
});


function grid( ) {
    var urljson = getbasepath() + "/setor/json/listsetorupgu";
    if (typeof myTable == 'undefined') {
        myTable = $('#fungsitable').dataTable({
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
                {name: "tahunAnggaran", value: $('#tahun').val()}
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
                var tglKerja = getTanggal(aData['tglKerja']);
                var tglRekam = getTanggal(aData['tglRekam']);
                var tglApprove = getTanggal(aData['tglApprove']);
                var tglCetak = getTanggal(aData['tglCetak']);
                var tglSah = getTanggal(aData['tglSah']);
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                //var isceked = aData['isAktif'] == '1'?'checked':'';
                //var cekaktif = "<input type='checkbox' name='isaktif"+index+"' id='isaktif"+index+"' disabled  "+isceked+" />"
                
                //var pilih="<a class='icon-edit' href='"+getbasepath()+"/setor/editsetor/"+aData['idSetor']+"'  ></a> - <a class='icon-remove' href='"+getbasepath()+"/setor/deletesetor/"+aData['idSetor']+"' ></a>"
                var pilih="<a class='icon-edit' href='"+getbasepath()+"/setor/editsetorupgu/"+aData['idSetor']+"'  ></a>"
                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiSetor']));
                //$('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiSetor']));
                $('td:eq(2)', nRow).html(getTanggal(aData['tglSetor']),"dd-MM-yyyy");
                $('td:eq(6)', nRow).html(pilih );
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"},              
                {"mDataProp": "kodeSetor", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglSetor", "bSortable": false, format_date :"dd-mm-yyyy",sClass: "center"}, 
                {"mDataProp": "tahunAngg", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "kodeBeban", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilaiSetor", "bSortable": false, sClass: "right"},
                {"mDataProp": "idSetor", "bSortable": false, sClass: "center"}
            ]
        });
   
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


function getBebanSetorUP() {
    $.getJSON(getbasepath() + "/setor/json/getBebanSetorUP", {idskpd: $("#idskpd").val()},
    function(result) {

        var banyak = result.aData;
        
        console.log("getBebanSetorUP banyak == " + banyak);
        if (banyak > 4) { // kalo sudah ada UP
            $("#btnTambah").hide();
        } 
    });
}
