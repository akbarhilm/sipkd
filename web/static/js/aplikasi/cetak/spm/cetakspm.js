$(document).ready(function () {
    gridsppup();
});
function gridsppup() {
    var urljson = getbasepath() + "/cetakspm/json/getlistspmcetak";
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
                var namafile = kskpd + "-" + "SPM" + "-" + kj + "-" + aData['kodeBeban'] + "-" + aData['tahun'] + "-" + aData['spmUp']['noSpm'] + ".pdf"
                var stat = aData['status']
                var statcet = aData['nilaiSpp'];
                var statcet1 = aData['nilaispp'];

                $('td:eq(0)', nRow).html(index);
                // $('td:eq(1)', nRow).html(aData['id']);
                $('td:eq(1)', nRow).html(aData['spmUp']['noSpm']);
                $('td:eq(3)', nRow).html(aData['kodeJenis']);
                $('td:eq(2)', nRow).html(aData['kodeBeban']);

                //$('td:eq(9)', nRow).html(aData['tglSppCetak']);
                //$('td:eq(8)', nRow).html(aData['tglSppCetak'] != null && aData['tglSppCetak'] != '' && aData['tglSppCetak'] != 'undefined' ? moment.utc(aData['tglSppCetak'], 'SSS').format('DD-MM-YYYY') : '-');
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                var namaPptk = "<input type='hidden'    id='namaPptk" + aData['id'] + "' value='" + aData['namaPptk'] + "'  />";
                var nipPptk = "<input type='hidden'    id='nipPptk" + aData['id'] + "' value='" + aData['nipPptk'] + "'  />";
                var namaPa = "<input type='hidden'    id='namaBendahara" + aData['id'] + "' value='" + aData['namaBendahara'] + "'  />";
                var nipPa = "<input type='hidden'    id='nipBendahara" + aData['id'] + "' value='" + aData['nipBendahara'] + "'  />";
                //alert(nipPptk);
                //alert(nipPa);
                $('td:eq(6)', nRow).html(aData['namaPptk'] + nipPptk);
                var donlod = "-";
                var batal = "-";
                var cekprint = "-";
                //alert(statcet1);

                cekprint = "<input type='checkbox' name='cek'" + aData['id'] + "'  id='cek" + aData['id'] + "' value='" + aData['id'] + "' class='checkbox' />";
                if (stat == 'PENGAJUAN') {

                    cekprint = "<input type='checkbox' name='cek" + aData['id'] + "'  id='cek" + aData['id'] + "' value='" + aData['id'] + "' class='checkbox' />";
                    //alert(cekprint);
                } else if (stat == 'CETAK')
                {
                    donlod = "<a class='fancybox fancybox.iframe icon-book' href='" + getbasepath() + "/cetakspm/" + aData['kodeBeban'] + "/" + aData['kodeJenis'] + "/" + aData['id'] + "/" + aData['spmUp']['noSpm'] + "/" + iskpd + "/" + lskpd + "/" + namafile + "' ></a>";
                    batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalspd(" + aData['id'] + ",'" + aData['id'] + "') ></a>";
                    cekprint = "-";

                }
                $('td:eq(6)', nRow).html("<span id='namaPptk" + aData['id'] + "'>" + aData['namaPptk'] + nipPptk + "</span>");
                $('td:eq(7)', nRow).html(cekprint + namaPa + nipPa);
                $('td:eq(8)', nRow).html(donlod);
                $('td:eq(9)', nRow).html(batal);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                //{"mDataProp": "tahun", "bSortable": true, sClass: "center"},
                {"mDataProp": "spmUp.noSpm", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeBeban", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeJenis", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "nilaiSpp", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "status", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "namaPptk", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "id", "bSortable": false, sClass: "center"}
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

        var namaPptk = $("#namaPptk" + idspd).text();
        var nipPptk = $("#nipPptk" + idspd).val();
        var nipPa = $("#nipBendahara" + idspd).val();
        var namaPa = $("#namaBendahara" + idspd).val();
        //alert("panjang nip PPtk: "+nipPptk.length);
        //alert("panjang nip Bendahara: "+nipPa.length);

        if (namaPptk == null || namaPptk == '' || namaPptk == 'undefined' || $.trim(namaPptk).length < 1) {
            statuscek++;
        } else {
            var map = {"id": idspd,
                "namaPptk": namaPptk,
                "namaPa": namaPa,
                "nipPa": nipPa,
                "nipPptk": nipPptk
            }
            selected.push(map);
        }

    });
    if (statuscek == 0) {
        $.ajax({
            type: "POST",
            url: baseurl + "/cetakspm/json/insertspmcetak",
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


function batalspd(idspd, nospm) {
    var urlhapusspd = getbasepath() + "/cetakspm/json/hapusspmcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SPM dengan nomor " + nospm + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function (result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspd": idspd, "nospm": nospm})
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