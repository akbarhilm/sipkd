$(document).ready(function() {
    gridspjcetak();
});
function gridspjcetak() {
    var urljson = getbasepath() + "/spjsah/json/getlistspjsah";
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
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
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
                 var nilaispj = "<input type='hidden'    id='nilaispj" + aData['idSpj'] + "' value='" + aData['nilaiSpjUp'] + "'  />";  
                 var nomorspj = "<input type='hidden'    id='nomorspj" + aData['idSpj'] + "' value='" + aData['noSpj'] + "'  />";  
                // alert(nomorspj);
                var kodeskpd = $('#kodeSkpd').val();
                var iskpd = $('#idskpd').val();
                var nipPa = "<input type='hidden'    id='nipPenggunaAnggaran" + aData['idSpj'] + "' value='" + aData['nipPenggunaAnggaran'] + "'  />";
                var nrkPa = "<input type='hidden'    id='nrkPenggunaAnggaran" + aData['idSpj'] + "' value='" + aData['nrkPenggunaAnggaran'] + "'  />";
                var namaPa = "<input type='hidden'   id='namaPenggunaAnggaran" + aData['idSpj'] + "' value='" + aData['namaPenggunaAnggaran'] + "'  />";
                var nipV = "<input type='hidden'    id='nipVerifikator" + aData['idSpj'] + "' value='" + aData['nipVerifikator'] + "'  />";
                var nrkV = "<input type='hidden'    id='nrkVerifikator" + aData['idSpj'] + "' value='" + aData['nrkVerifikator'] + "'  />";
                var namaV = "<input type='hidden'   id='namaVerifikator" + aData['idSpj'] + "' value='" + aData['namaVerifikator'] + "'  />";

                var nju = "<input type='hidden'   id='nju" + aData['idSpj'] + "' value='" + aData['nilaiSpjUp'] + "'  />";
               // var njt = "<input type='hidden'   id='njt" + aData['idSpj'] + "' value='" + aData['nilaiSpjTu'] + "'  />";
               // var tnj = nilaispjup + nilaispjtu;
                
               // alert(nilaispjup);
              //  alert(nilaispjtu);
               // alert(tnj);
                
                var idskpd = $('#idskpd').val();
                var namafile = kodeskpd + "-" + "SPJ" + "-" + aData['noSpj'] + "-" + aData['bulan'] +"-"+aData['tahun']+ ".pdf"
                var cetakstatus = aData['statusCetak'];
                var statuscetak = String;
                if (cetakstatus == 1) {
                    statuscetak = "CETAK";
                    
                    var cekprint = cekprint = "<input type='radio' class='checkbox' name='cek'  id='cek" + aData['idSpj'] + "' value='" + aData['idSpj'] + "' />";
               }
                
                var unduh = "<a class='icon-book' href='" + getbasepath() + "/spjsah/" + aData['idSpj'] + "/" + aData['noSpj'] +"/"+aData['bulan']+"/" +iskpd+"/"+namafile + "' ></a>";
               
                var batal = "-";
          
                /*0<th>No</th>
                 1<th>SPJ No</th>
                 2<th>Nilai SPJ UP</th>
                 3<th>Nilai SPJ TU</th>
                 4<th>Bulan SPJ</th>
                 5<th>SPJ Nihil</th>
                 6<th>Status</th>
                 7<th>Tgl Cetak</th>
                 8<th>Pilih</th>
                 9<th>Unduh PDF</th>
                 10<th>Batal</th> */
                $('td:eq(2)', nRow).html(nilaispjup);
                $('td:eq(3)', nRow).html(nilaispjtu);
                $('td:eq(6)', nRow).html(statuscetak);
                $('td:eq(8)', nRow).html(unduh);
                $('td:eq(9)', nRow).html(cekprint + nipPa + nrkPa + namaPa + nipV + nrkV + namaV + nju + nomorspj);
            //    $('td:eq(9)', nRow).html(unduh);
              //  $('td:eq(10)', nRow).html(batal); html("<span id='namaPptk" + aData['id'] + "'>" + aData['namaPptk'] + nipPptk + "</span>")
              //  $('td:eq(0)', nRow).html(index);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpj", "bSortable": true, sClass: "center"},
                {"mDataProp": "noSpj", "bSortable": true, sClass: "center"},
                {"mDataProp": "idSpj", "bSortable": true, sClass: "kanan"},
                {"mDataProp": "v_spjbl", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "bulan", "bSortable": true, sClass: "center"},
                {"mDataProp": "nihil", "bSortable": false, sClass: "center"},
                {"mDataProp": "statusCetak", "bSortable": false, sClass: "center"},
                {"mDataProp": "tglCetak", "bSortable": false, sClass: "center", sDefaultContent: "-"},
               // {"mDataProp": "idSpj", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpj", "bSortable": false, sClass: "center"},
                {"mDataProp": "idSpj", "bSortable": false, sClass: "center"}
            ]
        });
      //  $("div.top").html("<span onclick=trigercetak()  class='Button btn dark' style='float: right'>Proses Sah</span>");
         $("div.top").html("<button id='prosespengesahan' onclick=submitvalidasi() class='btn .dark-stripe' style='float: right'>Proses Pengesahan</button>");
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


function submitvalidasi( ) {
    var baseurl = getbasepath();
    var selected = [];
    var selectednoskpd = [];
    var statuscek = 0;
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();
         var nju = $("#nju" + idspd).val();
        
        selected.push({"idspd":idspd,"nju":nju });
       
        var nospd = $("#nomorspj" + idspd).val();
        selectednoskpd.push(nospd);
           /// return false;    
        // alert(idspd);
       // alert(nospd);
       // alert(nju);
    });


   bootbox.confirm("Anda akan melakukan pengesahan untuk SPJ dengan nomor " + selectednoskpd.join(",") + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
              return      $.ajax({
               type: "POST",
                url: baseurl + "/spjsah/json/updatespjsah",
                contentType: "text/plain; charset=utf-8",
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(selected),
                beforeSend: function() {
                    console.log(" before submit " + new Date())

                }
            }).always(function(data) {
                 
                gridspjcetak();
                bootbox.alert(data.responseText);
                //--print
              //  selectednoskpd.forEach(function(nospd) {
                    //alert(nospd);
            //        printUrl(nospd);
           //     }
                      //  );

            });
        } else {
            //alert("fsdfsdf");
            bootbox.hideAll();
            return result;
        }
    });

}

function printUrl(nospd) {
    var url = getbasepath() + "/spjsah/printvalidasi/cetakpengesahan/" + nospd;
    printPage(url)
     
}
function closePrint() {
    document.body.removeChild(this.__container__);
}

function setPrint() {
    this.contentWindow.__container__ = this;
    this.contentWindow.onbeforeunload = closePrint;
    this.contentWindow.onafterprint = closePrint;
    this.contentWindow.print();
}

function printPage(sURL) {
    var oHiddFrame = document.createElement("iframe");
    oHiddFrame.onload = setPrint;
    oHiddFrame.style.visibility = "hidden";
    oHiddFrame.style.position = "fixed";
    oHiddFrame.style.right = "0";
    oHiddFrame.style.bottom = "0";
    oHiddFrame.src = sURL;
    document.body.appendChild(oHiddFrame);
}




function trigercetak() {
    var baseurl = getbasepath();
    var selected = [];
    var cetakstatus = 1;
    $('.checkbox:checked').each(function() {
        var idspj = $(this).val();
        var nipPa = $("#nipPenggunaAnggaran" + idspj).val();
        var nrkPa = $("#nrkPenggunaAnggaran" + idspj).val();
        var namaPa = $("#namaPenggunaAnggaran" + idspj).val();
        var nipV = $("#nipVerifikator" + idspj).val();
        var nrkV = $("#nrkVerifikator" + idspj).val();
        var namaV = $("#namaVerifikator" + idspj).val();
        var nju = $("#nju" + idspj).val();
       // alert("insert : "+nju);
      
        var map = {"idspj": idspj,
            "statuscetak": cetakstatus,
            "nipPenggunaAnggaran": nipPa,
            "nrkPenggunaAnggaran": nrkPa,
            "namaPenggunaAnggaran": namaPa,
            "nipVerifikator": nipV,
            "nrkVerifikator": nrkV,
            "namaVerifikator": namaV,
            "nju": nju
        
        }

        selected.push(map);
    });
    $.ajax({
        type: "POST",
        url: baseurl + "/spjsah/json/updatespjsah",
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

}
function batalcetakspj(idspj, nospj) {
    var urlhapusspd = getbasepath() + "/cetakspj/json/hapusspjcetak";
    bootbox.confirm("Anda akan membatalkan cetak data SPD dengan nomor " + nospj + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
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

