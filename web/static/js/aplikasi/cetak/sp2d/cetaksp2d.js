$(document).ready(function() {
    var timer = $.timer(function() {
        var idskpd = $("#idskpd").val();
        var level = $("#levelSkpd").val();
        alert(level);
        if (idskpd && idskpd != 'undefined' && $.trim(idskpd).length > 0) {
            gridsppup();
        }
    });
    // timer.set({time: 120000, autostart: true});
});

function gridsppup() {
    var urljson = getbasepath() + "/cetaksp2d/json/getlistsp2dcetak";
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
            "sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "namaskpd", value: $('#skpd').val()},
                {name: "kodeSkpd", value: $('#kodeSkpd').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "levelSkpd", value: $('#levelSkpd').val()},
                {name: "user", value: $('#user').val()},
                {name: "kproses", value: $('#kproses').val()}
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
                var kskpd = $('#kodeskpd').val();
                var iskpd = $('#idskpd').val();
                var lskpd = $('#skpd').val();
                var kpros = $('#kproses').val();
                var level = $('#levelSkpd').val();
                var user = $('#user').val();
                var objar = lskpd.split("/");
                
                var tahun = $('#tahun').val();
                var kj = aData['kodeJenis'];
                var c = aData['kodeJenis'];
                var donlod = "-";
                var batal = "-";
                var cekprint = "-";
                var namafile = "SP2D" + "-" + kj + "-" + aData['kodeBeban'] + "-" + tahun + "-" + aData['sp2d']['nomorSp2d'] + ".pdf"
                var stat = aData['sp2d']['kodeCetak']
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(aData['sp2d']['nomorSp2d']);
                $('td:eq(2)', nRow).html(getTanggal(aData['sp2d']['tglSp2d']));
                $('td:eq(3)', nRow).html(aData['kodeBeban']);
                $('td:eq(4)', nRow).html(aData['kodeJenis']);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['sp2d']['nilaiSp2d']));
                var namaPptk = "<input type='hidden'    id='namaPptk" + aData['sp2d']['idSp2d'] + "' value='" + aData['namaPptk'] + "'  />";
                var nipPptk = "<input type='hidden'    id='nipPptk" + aData['sp2d']['idSp2d'] + "' value='" + aData['nipPptk'] + "'  />";
                $('td:eq(6)', nRow).html(aData['sp2d']['kodeCetak']);
                $('td:eq(7)', nRow).html("<span id='namaPptk" + aData['sp2d']['idSp2d'] + "'>" + aData['namaPptk'] + nipPptk + "</span>");

                if (stat == 'BELUM CETAK') {
                    cekprint = "<input type='checkbox' name='cek" + aData['sp2d']['idSp2d'] + "'  id='cek" + aData['sp2d']['idSp2d'] + "' value='" + aData['sp2d']['idSp2d'] + "' class='checkbox' />";
                } else if (stat == 'CETAK')
                {

                    donlod = "<a class='icon-book' href='" + getbasepath() + "/cetaksp2d/" + aData['id'] + "/" + aData['kodeBeban'] + "/" + aData['kodeJenis'] + "/" + aData['sp2d']['idSp2d'] + "/" + aData['sp2d']['nomorSp2d'] + "/" + iskpd + "/" + level + "/" + kpros + "/" + aData['kodeNihil'] + "/" + namafile + "' ></a>";
                    batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalspd(" + aData['sp2d']['idSp2d'] + ",'" + aData['sp2d']['nomorSp2d'] + "','" + namafile + "') ></a>";
                    cekprint = "-";
                    //alert(donlod); 
                }
                $('td:eq(8)', nRow).html(cekprint);
                $('td:eq(9)', nRow).html(donlod);
                $('td:eq(10)', nRow).html(batal);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center"},
                {"mDataProp": "sp2d.nomorSp2d", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "sp2d.tglSp2d", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "kodeBeban", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "kodeJenis", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "sp2d.nilaiSp2d", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "sp2d.kodeCetak", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "namaPptk", "bSortable": true, sClass: "left", sDefaultContent: "-"},
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
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();

        var namaPptk = $("#namaPptk" + idspd).text();
        var nipPptk = $("#nipPptk" + idspd).val();
        
        if (namaPptk == null || namaPptk == '' || namaPptk == 'undefined' || $.trim(namaPptk).length < 1) {
            statuscek++;
        } else {
            var map = {"id": idspd,
                "namaPptk": namaPptk,
                "nipPptk": nipPptk
            };
            selected.push(map);
        }

    });
    if (statuscek == 0) {
        $.ajax({
            type: "POST",
            url: baseurl + "/cetaksp2d/json/updatesp2dcetak",
            contentType: "text/plain; charset=utf-8",
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(selected)
        }).always(function(data) {
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

function batalspd(idspd, nosp2d, namafile) {
    var urlhapusspd = getbasepath() + "/cetaksp2d/json/hapussp2dcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SP2D dengan nomor " + nosp2d + " di" + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspd": idspd, "nosp2d": nosp2d, "namafile": namafile})
            }).always(function(data) {
                gridsppup();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}