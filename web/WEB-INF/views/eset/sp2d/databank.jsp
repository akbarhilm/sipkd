<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .ui-datepicker-calendar {
    display: none;
    }
  </style>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar SP2D</div>
    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="tahun"  id="tahun"   class="form-control " size="5" value="${tahunAnggaran}"/>

                    </div>
                </div>
            </div>
            <div id="fwil" class="form-group">
                <label class="col-md-3 control-label">Wilayah :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <select id="wil">

                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Bank Status :</label>
                <div class="col-md-5">
                    <div class="input-group">
                      <select id="cbBankStatus" onchange="timepick(this.value)">
                          <option value="1">Berhasil</option>
                          <option value="9">Gagal</option>
                          <option value="0">No Response</option>
                      </select>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Bayar :</label>
                <div class="col-md-5">
                    <div class="input-group">
                       <input type="text"  name="tglb"  id="tglb"   size="15" />

                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" id="tmp" onclick='grid()'>Tampil</button>
                    
                    <button type="button" class="btn green" id="tmp2" onclick='cetak()'>Cetak</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="portlet box">
    
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar SP2D</div>

    </div>
    <div >
        <div class="top" style="float: right;margin-right:2%;margin-top: 1%">
           
                
        </div>
        
        <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                     <th>No</th> 
                     <th>TGL VALIDASI</TH>
                     <th>NO SP2D</th>
                     
                     <th >NAMA SKPD</th>
                    <th >NILAI SP2D</th>
                    <th>BANK<br>TUJUAN</th>
                     <th>NO REK TUJUAN</th>
                     <th>NAMA TUJUAN</th>
                     <th>NO REK PENGIRIM</th>
                    <th>NAMA PENGIRIM</th>
                    <th >BIT 11</th>
                    <th>BIT 12</th>
                    <th>KODE<br>RESPONSE</th>
                </tr>
               
            </thead>
            <tbody  >
            </tbody>
            <tfoot>
            <th colspan="4">Total :</th>
            <th><input type="text" readonly="true" class="number inputmoney" id="sumfooter"></th>
            <th colspan="5"></th>
            </tfoot>
        </table> 
    </div>    
</div>
    
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/eset/listdata.js"></script>  
