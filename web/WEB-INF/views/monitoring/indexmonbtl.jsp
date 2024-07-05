<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Laporan Rekon SPP BTL dan SIMPEG</a></li>
</ul>


<form:form   method="post" commandName="refcetak"  id="refcetak"   action="${pageContext.request.contextPath}/laporanlra/prosessimpan" class="form-horizontal">
    <div onload="" class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Laporan Rekon SPP BTL dan SIMPEG</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran : </label>
                <div class="col-md-4">
                    <!--input type="hidden" id="isadd" name="isadd"  /-->
                    <input id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
                
            <div class="form-group" id="panelskpd">
                <label class="col-md-3 control-label">SKPD :</label>
                <div class="col-md-4 col-md-9">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" />
                    <input name="skpdpop" id="skpdpop" type="text" size="75" readonly='true' value="${skpd.kodeSkpd} / ${skpd.namaSkpd}" class="m-wrap large"  />
                </div>
            </div>  
                    <%--
            <div class="form-group">
                <label class="col-md-3 control-label">Jenis/Macam Laporan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <select  id="jenislap"  >
                            <option value="0" selected>--Pilih--</option> 
                            <option value="1" >1 - Gaji</option> 
                            <option value="2" >2 - TKD</option>
                            <option value="3" >3 - Transport</option>
                            <option value="4" >4 - PPh</option>
                        </select>
                    </div>
                </div>  
            </div>
                    --%>
            <div class="form-group" id="paneltgl">
                <label class="col-md-3 control-label">Bulan Rekap Penghasilan :</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <select  id="bulan"  >
                            </select>
                        </div>
                    </div>
            </div>
            
            <div class="form-actions fluid" id="panelcetak">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn blue" onclick='cetak()' href="#" > Cetak</button>
                </div>
            </div>   

        </div>
    </div>                  
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/monitoring/indexmonbtl.js"></script>  

