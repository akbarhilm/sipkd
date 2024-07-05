$(document).ready(function () {
    gridsppup();
});
function gridsppup() {
    var urljson = getbasepath() + "/cetaknpd/json/getlistnpdcetak";
    if (typeof myTable == 'undefined') {
        myTable = $('#cetakspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"top"i>irt<"bottom"flp><"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function () {
                //$(".checkbox").hide();
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "kodeSkpd", value: $('#kodeSkpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "levelSkpd", value: $('#levelSkpd').val()}
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
                var kskpd = $('#kodeSkpd').val();
                var iskpd = $('#idskpd').val();
                var kj = aData['kodeJenis'];
                var lskpd = $('#level').val();
                var namafile = kskpd + "-" + "NPD" + "-"+ aData['tahun'] + "-" + aData['noNpd'] + ".pdf"
                var stat = aData['status'];
                var statcet = aData['idNpd'];
                var statcet1 = aData['nilaiNpd'];
                
                //alert(statcet);
              //  alert(statcet1);

                $('td:eq(0)', nRow).html(index);
                // $('td:eq(1)', nRow).html(aData['id']);
                $('td:eq(1)', nRow).html(aData['noNpd']);
                $('td:eq(2)', nRow).html(aData['tanggalNpd'] != null && aData['tanggalNpd'] != '' && aData['tanggalNpd'] != 'undefined' ? moment.utc(aData['tanggalNpd'], 'SSS').format('DD-MM-YYYY') : '-');
                $('td:eq(3)', nRow).html(aData['kegiatan']['idKegiatan']);
                $('td:eq(4)', nRow).html(aData['ketNpd']);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiNpd']));
                $('td:eq(6)', nRow).html(aData['status']);
                var namaPptk = "<input type='hidden'    id='namaPptk" + aData['idNpd'] + "' value='" + aData['namaPptk'] + "'  />";
                var nipPptk = "<input type='hidden'    id='nipPptk" + aData['idNpd'] + "' value='" + aData['nipPptk'] + "'  />";
               // alert(namaPptk);
             //   alert(nipPptk);
                $('td:eq(7)', nRow).html(aData['namaPptk'] + nipPptk);
                var donlod = "-";
                var batal = "-";
                var cekprint = "-";
               
                if (stat == 'BELUM CETAK') {

                    cekprint = "<input type='checkbox' name='cek" + aData['idNpd'] + "'  id='cek" + aData['idNpd'] + "' value='" + aData['idNpd'] + "' class='checkbox' />";
                    //alert(cekprint);
                } else if (stat == 'CETAK')
                {
                    donlod = "<a class='icon-book' href='" + getbasepath() + "/cetaknpd/" + aData['idNpd'] + "/" + aData['noNpd'] + "/" + iskpd + "/" + lskpd + "/" + namafile + "' ></a>";
                    batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalspd(" + aData['idNpd'] + ",'" + aData['idNpd'] + "') ></a>";
                    cekprint = "-";

                }
               // $('td:eq(7)', nRow).html("<span id='namaPptk" + aData['id'] + "'>" + aData['namaPptk'] + nipPptk + "</span>");
                //$('td:eq(7)', nRow).html(stat);
                $('td:eq(8)', nRow).html(cekprint + namaPptk + nipPptk);
                $('td:eq(9)', nRow).html(donlod);
                $('td:eq(10)', nRow).html(batal);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idNpd", "bSortable": false, sClass: "center"},
                //{"mDataProp": "tahun", "bSortable": true, sClass: "center"},
                {"mDataProp": "noNpd", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "tanggalNpd", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "ketNpd", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "nilaiNpd", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "status", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "namaPptk", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "idNpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "idNpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "idNpd", "bSortable": false, sClass: "center"}
            ]
        });
        $("div.top").html("<span onclick=trigercetak()  class='Button btn dark' style='float: right'>Proses Cetak</span>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function trigercetak() {
    var baseurl = getbasepath();
    var selected = [];
    var statuscek = 0;
    $('.checkbox:checked').each(function () {
        var idspd = $(this).val();

        var namaPptk = $("#namaPptk" + idspd).val();
        var nipPptk = $("#nipPptk" + idspd).val();
        //alert(namaPptk);
       // alert(nipPptk);

        if (namaPptk == null || namaPptk == '' || namaPptk == 'undefined' || $.trim(namaPptk).length < 1) {
            statuscek++;
        } else {
            var map = {"idNpd": idspd,
                "namaPptk": namaPptk,
                "nipPptk": nipPptk
            }
            selected.push(map);
        }

    });
    if (statuscek == 0) {
        $.ajax({
            type: "POST",
            url: baseurl + "/cetaknpd/json/insertnpdcetak",
            contentType: "text/plain; charset=utf-8",
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(selected)
        }).always(function (data) {
            gridsppup();
            bootbox.alert(data.responseText);
        });
    } else {
        bootbox.alert("Pejabat penandatangan wajib dipilih sebelum cetak!");
    }
}

function tampilcek(id) {
    $("#cek" + id).show();
}


function batalspd(idspd, nonpd) {
    var urlhapusspd = getbasepath() + "/cetaknpd/json/hapusnpdcetak";
    bootbox.confirm("Anda akan membatalkan cetak data Setoran dengan nomor " + nonpd + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function (result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idNpd": idspd, "nonpd": nonpd})
            }).always(function (data) {
                gridsppup();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}