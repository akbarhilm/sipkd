<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
        
    function ambilkodeaktif() {
        //var id = $("#idskpdpop").val();
        
        //window.localStorage.setItem("kodeaktifval",kodeaktif);
        //console.log("send kode aktif value == "+kodeaktif);
        
    }
</script>


<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Pilih  Kegiatan</div>
    </div>

    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="refspj"  id="refspj"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
            <input type="hidden" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" readOnly="true"/> 
            <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" />
            <input type="hidden"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.namaSkpd}" />
            <input type="hidden" id='idspj' name='idspj' value="${idspj}"   />
            <input type="hidden" id="banyakrinci" name="banyakrinci"  />
            <input type="hidden" id="rincirows" name="rincirows"  />
            <input type="hidden" id="totalspjhidden" name="totalspjhidden"  />
            <div class="form-group">
                <label class="col-md-3 control-label">Program:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input size="80" type="text" path="program.namaProgram"   id="namaProgram"  class="m-wrap large" readOnly="true"/> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan</label>
                <div class="col-md-9">
                    <form:input  type="hidden" id="idKegiatan" path="kegiatan.idKegiatan" name="idKegiatan" value="${idkegiatan}"   />
                    <form:input type="text"  path="kegiatan.namaKegiatan" name="namaKegiatan" id="namaKegiatan"  class="m-wrap large" size="80" readOnly="true"/>  &nbsp;<a  class="fancybox fancybox.iframe btn green"  onclick="ambilkodeaktif()" href="${pageContext.request.contextPath}/spj/pilihkegiatan/${refspj.idSpj}?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 

                </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Pagu UP </label>
                <div class="col-md-9"> 
                    <form:input  type="text"  path="nilaiPaguUp"  id="nilaiPaguUp"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    <label class="control-label"> Total SPP </label> 
                    <form:input  type="text"  path="totalSpp"  id="totalSpp"  class="input money" size="19" readOnly="true" style="text-align:right"/> &nbsp 
                    
                    <label class="control-label"> Total Entry SPJ </label> 
                    <form:input  type="text"  path="entrySpj"  id="entrySpj"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    
               </div>
               
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Sisa Uang </label>
                <div class="col-md-9"> 
                    <form:input  type="text"  path="sisaUang"  id="sisaUang"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    <label class="control-label"> Presen Sisa Penyerapan  </label> 
                    <form:input  type="text"  path="persentase"  id="persentase"  class="m-wrap large" size="7" readOnly="true" /> % &nbsp 
                   
               </div>
               
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Beban</label>
                <div class="col-md-9"> 
                    <input type="hidden" name="kodeBebanPop" id="kodeBebanPop" maxlength="4" size="10" value="${kodeBebanPop}" onchange="cekbeban(this.value)" /> 
            
                    <form:input  type="text"  path="kodeBeban"  id="kodeBeban"  class="m-wrap large" size="7" readOnly="true" /> 
                    <input type="hidden" id="Beban" name="Beban"  />
               </div>
            </div>
        </form:form>

        </div>
    </div>   
                 
    <form id="formspjrincikegiatan">
    <div class="portlet box">
        <div class="portlet-title">
        </div>
        <div class="form-actions fluid">
               <button type="button" class="btn blue" onclick='submitnilaispj()'>Simpan</button> 
               <label class=" col-md-offset-3 col-md-9" align="Right" >*)Simpan Data Perhalaman</label>
               
              <!-- <button type="button" class="btn blue" onclick='cek()'>CEK</button> -->
           
        </div>
        <div class="portlet-body">
            
            <table id="kegiatantable" class="table table-striped table-bordered table-condensed table-hover " style="width: 1000px;" >
                <thead>
                    <tr>
                        <th >No</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai SPD</th>
                        <th>Nilai LS</th>
                        <th>Nilai TU</th>
                        <th>Sudah SPJ</th>
                        <th>Sisa Belum SPJ</th>
                        <th>Nilai SPJ</th>
                       
                        <th></th> 
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th colspan="8" style="text-align:right">Total:</th>
                       <!-- <th colspan="1">
                            <input  type='text' id="totalsisaspj"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>-->
                        <th colspan="1" >
                            <input type='text' id="sumspjtu"   name="sumspjtu" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>
                </tfoot>
                <tbody>
                </tbody> 
            </table>
            
            <table id="upgukegiatantable" class="table table-striped table-bordered table-condensed table-hover " style="width: 1000px;" >
                <thead>
                    <tr>
                        <th >No</th>
                        <th>Kode Akun</th>
                        <th>Nama Akun</th>
                        <th>Nilai SPD</th>
                        <th>Nilai LS</th>
                        <th>Sudah SPJ</th>
                        <th>Sisa Belum SPJ</th>
                        <th>Nilai SPJ</th>
                        <th></th> 
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th colspan="7" style="text-align:right">Total:</th>
                       <!-- <th colspan="1">
                            <input  type='text' id="totalsisaspj"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>-->
                        <th colspan="1" >
                            <input type='text' id="sumspjup"   name="sumspjup" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:90%;'    />
                        </th>
                    </tr>
                </tfoot>
                <tbody>
                </tbody> 
            </table>
        </div>
    </div>
    </form>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spj/addkegiatan.js"></script>  
