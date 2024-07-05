$(document).ready(function() {
    gridspjcetak();
});
function gridspjcetak() {
    var urljson = getbasepath() + "/cetakspj/json/getlistspjcetak";
    if (typeof myTable == 'undefined') {
        myTable = $('#cetakspjtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
            "iDisplayLength": 25,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                 {name: "kodeSkpd", value: $('#kodeSkpd').val()},
                {name: "tahun", value: $('#tahun').val()}
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
                var cekaktif = "<input type='checkbox' name='isaktif" + index + "' id='isaktif" + index + "' disabled  " + isceked + " />"
                var edit = "<a href='" + getbasepath() + "/spj/editspjbl/" + aData['idSpj'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/spj/deletespjbl/" + aData['idSpj'] + "' class='icon-remove' ></a>";
                var nilaispjup = accounting.formatNumber(aData['nilaiSpjUp'], 2, '.', ",");
                var nilaispjtu = accounting.formatNumber(aData['nilaiSpjTu'], 2, '.', ",");
                var nilaipaguup = accounting.formatNumber(aData['nilaiPaguUp'], 2, '.', ",");
                var a = aData['nilaiPaguUp'];
                //alert(a);
                var x = (85/100)*100;
                var xx = x*a;
                //alert(xx);
                var kodeskpd = $('#kodeSkpd').val();
                var iskpd = $('#idskpd').val();
                var nipPa = "<input type='hidden'    id='nipPenggunaAnggaran" + aData['idSpj'] + "' value='" + aData['nipPenggunaAnggaran'] + "'  />";
                var nrkPa = "<input type='hidden'    id='nrkPenggunaAnggaran" + aData['idSpj'] + "' value='" + aData['nrkPenggunaAnggaran'] + "'  />";
                var namaPa = "<input type='hidden'   id='namaPenggunaAnggaran" + aData['idSpj'] + "' value='" + aData['namaPenggunaAnggaran'] + "'  />";
                var nipV = "<input type='hidden'    id='nipVerifikator" + aData['idSpj'] + "' value='" + aData['nipVerifikator'] + "'  />";
                var nrkV = "<input type='hidden'    id='nrkVerifikator" + aData['idSpj'] + "' value='" + aData['nrkVerifikator'] + "'  />";
                var namaV = "<input type='hidden'   id='namaVerifikator" + aData['idSpj'] + "' value='" + aData['namaVerifikator'] + "'  />";
                var npu = "<input type='hidden'   id='npu" + aData['idSpj'] + "' value='" + aData['nilaiPaguUp'] + "'  />";
                var nspj = "<input type='hidden'   id='nspj" + aData['idSpj'] + "' value='" + aData['nilaiSpjUp'] + "'  />";
               //alert(npu);
                var idskpd = $('#idskpd').val();
                var namafile = kodeskpd + "-" + "SPJ" + "-" + aData['noSpj'] + "-" + aData['bulan'] +"-"+aData['tahun']+ ".pdf"
                var cetakstatus = aData['statusCetak'];
                var idspjsah = aData['idSpjsah'];
                var nospj = aData['noSpj'];
                console.log("id SPJ Sah dan No SPJ nya = "+idspjsah+"  "+nospj+"  "+cetakstatus);
               // JIKA idSpjsah > 0 --> TIDAK DAPAT DI-DELETE (DASH)
                // JIKA idSpjsah =-1 AND  STATUSCETAK<>1 --> TIDAK DAPAT DI-DELETE (DASH)
                
                var cekprint = "-";
                var unduh = "-";
                var batal = "-";
                
                if ( ((idspjsah > '0'))   )
                {
                    unduh = "<a class='icon-book' href='" + getbasepath() + "/cetakspj/" + aData['idSpj'] + "/" + aData['noSpj'] +"/"+aData['bulan']+"/" +iskpd+"/"+namafile + "' ></a>";
                    batal = "-";
                } else if ((idspjsah == '-1') && (cetakstatus == '1')) 
                {
                     unduh = "<a class='icon-book' href='" + getbasepath() + "/cetakspj/" + aData['idSpj'] + "/" + aData['noSpj'] +"/"+aData['bulan']+"/" +iskpd+"/"+namafile + "' ></a>";
                     batal =  "<a class='icon-remove linkpilihan' href='#' onclick=batalcetakspj(" + aData['idSpj'] + ",'" + aData['noSpj'] + "') ></a>";;
                }
                else 
                {
                    cekprint = "<input type='checkbox' class='checkbox' name='cek" + aData['idSpj'] + "'  id='cek" + aData['idSpj'] + "' value='" + aData['idSpj'] + "' />";
                }
                
                
                               
                var statuscetak = String;
                if (cetakstatus == 1) {
                    statuscetak = "CETAK";
                } else
                {
                    statuscetak = "PENGAJUAN";
                }
               
                
                
            /*    if (cetakstatus == '1') {
                    //var donlod = aData['status'] == 'CETAK' || aData['status'] == 'VALIDASI' ? "<a href='" + getbasepath() + "/cetakspp/" + aData['kodeBeban'] +"/" + aData['id'] + "/" + aData['noSpp'] + "/" +namafile+" ' class='fancybox fancybox.iframe icon-book' ></a>" : '';
                    unduh = "<a class='icon-book' href='" + getbasepath() + "/cetakspj/" + aData['idSpj'] + "/" + aData['noSpj'] +"/"+aData['bulan']+"/" +iskpd+"/"+namafile + "' ></a>";
                    batal = "<a class='icon-remove linkpilihan' href='#' onclick=batalcetakspj(" + aData['idSpj'] + ",'" + aData['noSpj'] + "') ></a>";
                } else
                {
                    cekprint = "<input type='checkbox' class='checkbox' name='cek" + aData['idSpj'] + "'  id='cek" + aData['idSpj'] + "' value='" + aData['idSpj'] + "' />";
                }*/
                
                $('td:eq(3)', nRow).html(nilaispjup);
                $('td:eq(4)', nRow).html(nilaispjtu);
                $('td:eq(5)', nRow).html(nilaipaguup);
                
                $('td:eq(7)', nRow).html(statuscetak);
                
                $('td:eq(9)', nRow).html(cekprint + nipPa + nrkPa + namaPa + nipV + nrkV + namaV + npu + nspj);
                $('td:eq(10)', nRow).html(unduh);
                $('td:eq(11)', nRow).html(batal);
                
                $('td:eq(0)', nRow).html(index);
                
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpj", "bSortable": true, sClass: "center"},
                {"mDataProp": "bulan", "bSortable": true, sClass: "center"},
                {"mDataProp": "noSpj", "bSortable": true, sClass: "center"},
                {"mDataProp": "idSpj", "bSortable": true, sClass: "kanan"},
                {"mDataProp": "v_spjbl", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "nilaiPaguUp", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "nihil", "bSortable": false, sClass: "center"},
                {"mDataProp": "statusCetak", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglCetak", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "idSpj", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpj", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpj", "bSortable": false, sClass: "center"}
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


function pindahhalaman() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spj/addspjbl/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spj/addspjbl");
    }

}
function trigercetak() {
    var baseurl = getbasepath();
    var selected = [];
    var cetakstatus = 0;
    $('.checkbox:checked').each(function() {
        var idspj = $(this).val();
        var nipPa = $("#nipPenggunaAnggaran" + idspj).val();
        var nrkPa = $("#nrkPenggunaAnggaran" + idspj).val();
        var namaPa = $("#namaPenggunaAnggaran" + idspj).val();
        var nipV = $("#nipVerifikator" + idspj).val();
        var nrkV = $("#nrkVerifikator" + idspj).val();
        var namaV = $("#namaVerifikator" + idspj).val();
        
        var npu = $("#npu" + idspj).val();
        var nspj = $("#nspj" + idspj).val();
       // var x = 0.80;                          ini untuk checking 85%
      //  var xx = x*npu;                        ini untuk checking 85%
        
       // alert("1 : "+npu);
       // alert("2 : "+nspj);
      //  alert("3 : "+x);
       // alert("4 : "+xx);
        //console.log(nspj < xx);
     //  if (nspj < xx) {                  ini untuk checking 85%
         //  alert("ulang");
       //     cetakstatus++;               ini untuk checking 85%
     //   } else {                         ini untuk checking 85%
           // alert("masuk");
            var map = {"idspj": idspj,
            "statuscetak": cetakstatus,
            "nipPenggunaAnggaran": nipPa,
            "nrkPenggunaAnggaran": nrkPa,
            "namaPenggunaAnggaran": namaPa,
            "nipVerifikator": nipV,
            "nrkVerifikator": nrkV,
            "namaVerifikator": namaV,
            "npu": npu,
            "nspj": nspj
            }
            selected.push(map);
     //   }                                     ini untuk checking 85%
  
    });
  if (cetakstatus == 0) {
    $.ajax({
        type: "POST",
        url: baseurl + "/cetakspj/json/insertspjcetak",
        contentType: "text/plain; charset=utf-8",
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(selected)
    }).always(function(data) {
        gridspjcetak();
        bootbox.alert(data.responseText);
    });
}// else          ini untuk checking 85%
//{               ini untuk checking 85%
//    bootbox.alert("Nilai SPJ Kurang dari 85% dari PAGU UP..! Silahkan Menambah Lagi Nilai SPJ nya..");  ini untuk checking 85%
//}               ini untuk checking 85%

}
function batalcetakspj(idspj, nospj) {
    var urlhapusspd = getbasepath() + "/cetakspj/json/hapusspjcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SPD dengan nomor " + nospj + " di" + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspj": idspj, "nospj": nospj})
            }).always(function(data) {
                gridspjcetak();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}

