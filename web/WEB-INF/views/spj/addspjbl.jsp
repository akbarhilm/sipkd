<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
    $(document).ready(function () {
        getsudahspj(${bulanadaspj});

    });
</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/spj/indexspj">Daftar SPJ</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Pengajuan SPJ Belanja Langsung</a></li>
</ul>


<form:form   method="post" commandName="refsppup"  id="refsppup"   action="${pageContext.request.contextPath}/spj/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Pengajuan SPJ Belanja Langsung</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="idSpj" id='idSpj'    />
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input type="hidden" id="adanilaispj" name="adanilaispj" value="${adanilaispj}"  />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)" value="${skpd.idSkpd}"  />
                        <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="100" maxlength="110" readonly='true'  />
                        <c:if test="${isall ==1}"  >&nbsp;  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a>
                            <form:errors path="skpd.idSkpd" class="label label-important" /></c:if>
                        </div>
                    </div>
                </div>     
                <div class="form-group">
                    <label class="col-md-3 control-label">No. SPJ</label>
                    <div class="col-md-4">
                        <div class="input-group">
                        <form:input path="noSpj" id="noSpj" type="text" size="25" maxlength="20" readonly='true'  /> 
                    </div>
                </div>
            </div>       
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan SPJ :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="comparebulan" name="comparebulan" value="${bulanadaspj}"  />
                        <input type="hidden" id="nihilnihil" name="nihilnihil" value="${nihilnihil}"  />

                        <form:select  path="bulan" id="bulan" onchange="getsudahspj(${bulanadaspj})" >  
                            <form:options items="${listBulan}"  itemValue="kodeBulan" itemLabel = "namaBulan"  />
                        </form:select >
                        <form:errors path="bulan" cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Bendahara :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="bendahara.nama" id="bendahara" cssClass="required"   size="55"   readonly='true'  /> 
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SPJ NIHIL :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="nihil" id="spjnihil">

                        </form:select>
                        <form:errors path="nihil"  cssClass="error"  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input  cssClass="required" path="ketSpj" id="ketSpj"  size="90" maxlength="400"   />  
                        <form:errors path="ketSpj"  cssClass="error"  />
                    </div>
                </div>
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Pagu UP </label>
                <div class="col-md-9"> 
                    <form:input  type="text"  path="nilaiPaguUp"  id="nilaiPaguUp"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    <label class="control-label"> Total SPP </label> 
                    <form:input  type="text"  path="totalSpp"  id="totalSpp"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    <label class="control-label"> Total Entry SPJ </label> 
                    <form:input  type="text"  path="entrySpj"  id="entrySpj"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
               </div>
               
            </div>
                    
            <div class="form-group">
                <label class="col-md-3 control-label">Sisa Uang </label>
                <div class="col-md-9"> 
                    <form:input  type="text"  path="sisaUang"  id="sisaUang"  class="input money" size="19" readOnly="true" style="text-align:right" /> &nbsp 
                    
                    <label class="control-label"> Persen Sisa Penyerapan  </label> 
                    <form:input  type="text"  path="persentase"  id="persentase"  class="m-wrap large" size="7" readOnly="true" /> % &nbsp 
                   
                    
               </div>
               
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Simpan':'Simpan'}</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="${pageContext.request.contextPath}/spj/indexspj">Kembali</a>
                </div>
            </div>

        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spj/addspjbl.js"></script>  