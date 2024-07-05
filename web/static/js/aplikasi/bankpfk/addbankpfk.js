$(document).ready(function() {
    grid();
});

function grid() {
    var urljson = getbasepath() + "/bankpfk/json/getlistbankcabang";
    if (typeof myTable == 'undefined') {
        myTable = $('#banktable').dataTable({
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
                        {name: "idinduk", value: $('#idBank').val()},
                {name: "nama", value: $('#namacabang').val()},
                {name: "alamat", value: $('#alamatcabang').val()}
                );
            }, "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {


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
                //var alamat =  aData['alamatBank'];

                var pilihan = "<a class='icon-remove' onclick='hapus(" + index + ")' ></a>";
                var idcabang = "<input type='hidden' id='idcabang" + index + "' value='" + aData['idBank'] + "'/>";
                var namacabang = "<input type='text' id='namacabang" + index + "' value='" + aData['namaBank'] + "'  readonly='true'  size='60' />";
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='penanda" + index + "' id='penanda" + index + "' onchange=enablerow(this," + index + ") />- <button type='button' id='ubah" + index + "' class='btn-mini blue' disabled='true' onclick='ubahperbaris(" + index + ")'>Ubah</button>";
                var alamat = "<textarea id='alamat" + index + "'readonly style='border:none;margin:0;width:650px;'>" + aData['alamatBank'] + "</textarea>";

                $('td:eq(0)', nRow).html(index + idcabang);
                $('td:eq(1)', nRow).html(namacabang);
                $('td:eq(2)', nRow).html(alamat);
                $('td:eq(3)', nRow).html(inputcek);
                $('td:eq(4)', nRow).html(pilihan);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBank", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaBank", "bSortable": false, sClass: "left"},
                {"mDataProp": "alamatBank", "bSortable": false, sClass: "left"},
                {"mDataProp": "idBank", "bSortable": false, sClass: "center"},
                {"mDataProp": "idBank", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

function enablerow(obj, index) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
        $("#penanda" + index).val(1);
    } else {
        $("#penanda" + index).val(0);
    }
    setdisabled(param, index);

}
function setdisabled(param, index) {
    $("#namacabang" + index).attr("readonly", param);
    $("#alamat" + index).attr("readonly", param);
    $("#ubah" + index).attr("disabled", param);
}

function hapus(id) {
    var idbank = $('#idcabang' + id).val();

    bootbox.confirm("Apakan Anda Yakin Akan Menghapus Data " + $("#namacabang" + id).val() + " ?", function(result) {
        if (result) {

            return   $.ajax({
                type: "POST",
                url: getbasepath() + "/bankpfk/json/prosesdelete",
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idbank": idbank})

            }).always(function(data) {
                bootbox.alert("Data Bank Berhasil Dihapus");
                grid();
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });
}

function carialamat() {
    $("#alamatcabang").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 1 || panjang == 0) {
            grid();
        }
    });
}

function carinama() {
    $("#namacabang").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            grid();
        }
    });
}

function cekLengkap() {
    var alamat = $("#alamatcabang").val();
    var nama = $("#namacabang").val();
    var ketnama = $("#ketnama").val();

    if (nama == "" || ketnama == "") {
        bootbox.alert("Nama Bank Tidak Boleh Kosong");
    } else {
        simpan();
    }
}

function simpan() {

    var varurl = getbasepath() + "/bankpfk/json/prosessimpancabang";
    var dataac = [];
    var namabank;

    namabank = $("#ketnama").val() + " " + $("#namacabang").val();

    var datajour = {
        kodebanktf: $("#kodeBankTransfer").val(),
        namabanktf: $("#namaBankTransfer").val(),
        kodebank: "",
        namabank: namabank,
        kodebankrtgs: "",
        namabankrtgs: "",
        kodeaktif: "1",
        alamat: $("#alamatcabang").val(),
        idinduk: $("#idBank").val()
    }

    dataac = datajour;

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
        bootbox.alert("Data Bank Cabang Berhasil Disimpan");
        grid();
    });

}


function cekubah() {
    var table = document.getElementById('banktable');
    var rows = table.rows;
    var jum = rows.length;
    var banyakcek = 0;
   
    if (jum > 1) {

        for (var a = 1; a < jum; a++) {
            //console.log("penanda ke - "+a);
            if ($('#penanda' + a).val() == 1) {
                banyakcek = banyakcek + 1;
            }
        }
        
        if (banyakcek == 0) {
            datalengkap = false;
        } else {
             ubah();
        }
    }
}

function ubah() {

    var table = document.getElementById('banktable');
    var rows = table.rows;
    var jum = rows.length;
    
    var jumSimpan = 0;
    var varurl = getbasepath() + "/bankpfk/json/editcabang";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < jum-1; i++) { // list
        var id = i + 1;
        //console.log(id + "-(idkomp + id).val = " + $("#idkomp" + id).val());
        if ($("#penanda" + id).val() == 1) {
            
            var pararr = {
                namabank: $("#namacabang" + id).val(),
                alamat: $("#alamat" + id).val(),
                idbank: $("#idcabang" + id).val()
            };
            nilailist[jumSimpan] = pararr;
            jumSimpan += 1;
        }
    }

    var datajour = {
        kodebanktf: $("#kodeBankTransfer").val(),
        namabanktf: $("#namaBankTransfer").val(),
        kodebank: "",
        kodebankrtgs: "",
        namabankrtgs: "",
        kodeaktif: "1",
        idinduk: $("#idBank").val(),
        nilailist: nilailist
    }
    dataac = datajour;

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
        bootbox.alert("Data Bank Cabang Berhasil Diubah");
        grid();
    });

}

function ubahperbaris(id) {

    var varurl = getbasepath() + "/bankpfk/json/prosesupdate";
    var dataac = [];
    
    var datajour = {
        kodebanktf: $("#kodeBankTransfer").val(),
        namabanktf: $("#namaBankTransfer").val(),
        kodebank: "",
        namabank: $("#namacabang" + id).val(),
        kodebankrtgs: "",
        namabankrtgs: "",
        kodeaktif: "1",
        alamat: $("#alamat" + id).val(),
        idinduk: $("#idBank").val(),
        idbank: $("#idcabang" + id).val()
       
    }

    dataac = datajour;

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
        bootbox.alert("Data Bank Cabang Berhasil Diubah");
        grid();
    });

}

