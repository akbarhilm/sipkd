$(document).ready(function() {
    gridspj();
    setbtnproses();
    //bootbox.alert("CEK NILAI TAHUN = "+$('#tahun').val());
    //bootbox.alert("CEK NILAI IDSKPD = "+$('#idskpd').val());
    
});
function setbtnproses(){
    var bulan = $('#idSpj').val();
    
    if (bulan == ""){
        $('#buttoninduk').attr("disabled", true);
    }
}

function gridspj() {
    var urljson = getbasepath() + "/spj/json/getlistjournalspj";
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
                       // {name: "idskpd", value: $('#idskpd').val()},
                    {name: "tahun", value: $('#tahun').val()},
                    {name: "bulan", value: $('#bulan').val()},
                    {name: "idSpj", value: $('#idSpj').val()}
                
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
                var nilaispj = accounting.formatNumber(aData['nilai_spj'], 2, '.', ",");
                $('td:eq(0)', nRow).html(index);
                //$('td:eq(3)', nRow).html(nilaispjbtl);
                $('td:eq(5)', nRow).html(nilaispj);
                //$('td:eq(5)', nRow).html(cekaktif);
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpj", "bSortable": true, sClass: "center"},
                {"mDataProp": "kodekeg", "bSortable": true, sClass: "center"},
                {"mDataProp": "namakeg", "bSortable": true, sClass: "left"},
                {"mDataProp": "akunbelanja", "bSortable": true,  sClass: "left"},
                {"mDataProp": "akunjurnal", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilai_spj", "bSortable": false, sClass: "kanan"}
           
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function submitnilai( ) {
    var varurl = getbasepath() + "/spj/json/prosessimpan";
    var dataac = [];
    var datapeg = {
        bulan: $("#bulan").val(),
        idSpj: $("#idSpj").val(),
        idskpd: $("#idskpd").val(),
        tahun: $("#tahun").val()
        
    }
    dataac = datapeg;

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
        //grid();
        bootbox.alert("Proses Journal Berhasil Dilakukan");
    });
}

function pindahhalaman() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spj/addspjbl/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spj/addspjbl");
    }

}

