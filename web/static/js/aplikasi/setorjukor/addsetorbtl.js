$(document).ready(function() {
    var ta = (parseInt($('#tahun').val()) - 1);
    $('#tahunAngg').val(ta);

    
    if ($('#tahun').val() == "2018") {
        getIdskpdLama();
    }
    
    var uraian = "Pengembalian Sisa Belanja Tidak Langsung Tahun Lalu (" + ta + ")";
    $('#keterangan').val(uraian);

});

function getIdskpdLama() {
    $.getJSON(getbasepath() + "/setorjukor/json/getIdskpdLama", {idskpd: $("#idskpd").val(), tahunAnggaran: $("#tahunAngg").val()},
    function(result) {
        $('#idskpdlama').val(result);
    });
}

function getbanyaksetorrinci() {
    var idskpdval;

    if ($('#tahun').val() == "2018") {
        idskpdval = $('#idskpdlama').val();
    } else {
        idskpdval = $('#idskpd').val();
    }

    $.getJSON(getbasepath() + "/setorjukor/json/getbanyakstsbtl", {idskpd: idskpdval, tahun: $("#tahunAngg").val(), nojurnal: $("#noJurnal").val()},
    function(result) {
        $('#banyakrinci').val(result);
        // bootbox.alert("banyakrinci = "+ $('#banyakrinci').val());
    });
}

function gridsetor() {
    var urljson = getbasepath() + "/setorjukor/json/getListAkunJukorBtl";

    var idskpdval;
    
    if ($('#tahun').val() == "2018") {
        idskpdval = $('#idskpdlama').val();
    } else {
        idskpdval = $('#idskpd').val();
    }
    
    if (typeof myTable2 == 'undefined') {
        myTable2 = $('#forrinci2016').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 100,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                formatnumberonkeyup();
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: idskpdval},
                {name: "tahun", value: $('#tahunAngg').val()},
                {name: "nojurnal", value: $('#noJurnal').val()}
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
                
                var inputnilaisetor = "<input type='text' id='nilaisetor" + index + "' name='nilaisetor" + index + "'  class='inputmoney' value='" + aData['nilaiSetor'] + "'  readOnly='true' />";
                
                var idbas = "<input type='hidden' value='" + aData['idAkun'] + "' id='idAkun" + index + "' name='idAkun" + index + "' >";

                $('td:eq(2)', nRow).html(inputnilaisetor + idbas);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sDefaultContent: "-", sClass: "kanan"}
            ]
        });

    }
    else
    {
        myTable2.fnClearTable(0);
        myTable2.fnDraw();
    }
    
    getbanyaksetorrinci();
}
